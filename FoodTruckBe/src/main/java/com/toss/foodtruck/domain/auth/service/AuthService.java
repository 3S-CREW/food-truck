package com.toss.foodtruck.domain.auth.service;

import com.toss.foodtruck.domain.auth.dto.LoginRequestDto;
import com.toss.foodtruck.domain.auth.dto.TokenResponseDto;
import com.toss.foodtruck.domain.auth.dto.TossResponseDto;
import com.toss.foodtruck.domain.member.entity.Member;
import com.toss.foodtruck.domain.member.repository.MemberRepository;
import com.toss.foodtruck.global.enums.Role;
import com.toss.foodtruck.global.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    // mTLS 인증서가 설정된 RestTemplate
    private final RestTemplate restTemplate;

    @Value("${toss.url.token}")
    private String tossTokenUrl;

    @Value("${toss.url.profile}")
    private String tossProfileUrl;

    // [중요] 생성자 주입을 명시적으로 작성해야 @Qualifier를 쓸 수 있습니다.
    // (Lombok @RequiredArgsConstructor로는 @Qualifier 지정이 어렵습니다)
    public AuthService(MemberRepository memberRepository,
                       JwtTokenProvider jwtTokenProvider,
                       RedisTemplate<String, Object> redisTemplate,
                       @Qualifier("tossRestTemplate") RestTemplate restTemplate) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTemplate = redisTemplate;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto request) {
        // [수정] DTO에서 referrer도 같이 받아야 함
        TossResponseDto.UserProfile tossUser = getTossUserInfo(request.getAuthCode(), request.getReferrer());

        log.info("토스 로그인 성공: accountId={}", tossUser.getAccountId());

        Member member = memberRepository.findByAccountId(tossUser.getAccountId())
                                        .orElseGet(() -> signup(tossUser));

        member.updateInfo(tossUser.getName(), tossUser.getPhoneNumber());

        String accessToken = jwtTokenProvider.createAccessToken(member.getId(), member.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());

        redisTemplate.opsForValue()
                     .set("RT:" + member.getId(), refreshToken,
                         jwtTokenProvider.getRefreshTokenValidityInMilliseconds(), TimeUnit.MILLISECONDS);

        return TokenResponseDto.builder()
                               .grantType("Bearer")
                               .accessToken(accessToken)
                               .refreshToken(refreshToken)
                               .accessTokenExpiresIn(jwtTokenProvider.getTokenValidityInMilliseconds())
                               .build();
    }

    private Member signup(TossResponseDto.UserProfile tossUser) {
        return memberRepository.save(Member.builder()
                                           .accountId(tossUser.getAccountId())
                                           .name(tossUser.getName())
                                           .phoneNumber(tossUser.getPhoneNumber())
                                           .ci(tossUser.getCi())
                                           .role(Role.USER)
                                           .build());
    }

    private TossResponseDto.UserProfile getTossUserInfo(String authCode, String referrer) {
        // A. 토큰 발급 (mTLS 통신)
        String tossAccessToken = requestTossAccessToken(authCode, referrer);
        // B. 정보 조회 (mTLS 통신)
        return requestTossUserProfile(tossAccessToken);
    }

    // URL: /api-partner/v1/apps-in-toss/user/oauth2/generate-token
    private String requestTossAccessToken(String authCode, String referrer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("authorizationCode", authCode);

        // [수정] 프론트에서 받은 referrer 값 사용 (없으면 빈 문자열이라도 보내야 함)
        body.put("referrer", referrer != null ? referrer : "");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            // 여기서 사용하는 restTemplate은 TossConfig에서 만든 '인증서 탑재된' 녀석입니다.
            ResponseEntity<TossResponseDto.Token> response = restTemplate.postForEntity(
                tossTokenUrl, request, TossResponseDto.Token.class);

            return response.getBody().getAccessToken();

        } catch (HttpClientErrorException e) {
            log.error("토스 토큰 발급 실패: {}", e.getResponseBodyAsString());
            throw new RuntimeException("토스 로그인 실패 (토큰 발급)");
        }
    }

    private TossResponseDto.UserProfile requestTossUserProfile(String tossAccessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tossAccessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<TossResponseDto.UserProfile> response = restTemplate.exchange(
                tossProfileUrl,
                HttpMethod.GET,
                request,
                TossResponseDto.UserProfile.class
            );
            return response.getBody();

        } catch (HttpClientErrorException e) {
            log.error("토스 유저 정보 조회 실패: {}", e.getResponseBodyAsString());
            throw new RuntimeException("토스 로그인 실패 (정보 조회)");
        }
    }
}