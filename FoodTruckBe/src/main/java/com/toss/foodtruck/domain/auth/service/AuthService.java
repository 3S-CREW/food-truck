package com.toss.foodtruck.domain.auth.service;

import com.toss.foodtruck.domain.auth.dto.LoginRequestDto;
import com.toss.foodtruck.domain.auth.dto.TokenResponseDto;
import com.toss.foodtruck.domain.auth.dto.TossResponseDto;
import com.toss.foodtruck.domain.member.entity.Member;
import com.toss.foodtruck.domain.member.repository.MemberRepository;
import com.toss.foodtruck.global.enums.Role;
import com.toss.foodtruck.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RestTemplate restTemplate;

    @Value("${toss.client-id}")
    private String tossClientId;

    @Value("${toss.client-secret}")
    private String tossClientSecret;

    @Value("${toss.url.token}")
    private String tossTokenUrl;

    @Value("${toss.url.profile}")
    private String tossProfileUrl;

    // 실제 로그인 로직
    @Transactional
    public TokenResponseDto login(LoginRequestDto request) {

        // 1. 토스 API 연동 (AuthCode -> Token -> UserInfo)
        TossResponseDto.UserProfile tossUser = getTossUserInfo(request.getAuthCode());
        log.info("토스 유저 정보 가져오기 성공: accountId={}", tossUser.getAccountId());

        // 2. DB 조회 및 회원가입/로그인 처리
        Member member = memberRepository.findByAccountId(tossUser.getAccountId())
                                        .orElseGet(() -> signup(tossUser));

        // 정보 업데이트 (이름, 전화번호 등 변경되었을 수 있음)
        member.updateInfo(tossUser.getName(), tossUser.getPhoneNumber());

        // 3. 우리 서버 전용 JWT 토큰 발급
        String accessToken = jwtTokenProvider.createAccessToken(member.getId(), member.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());

        // 4. Redis에 Refresh Token 저장
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

    // 신규 회원가입 로직
    private Member signup(TossResponseDto.UserProfile tossUser) {
        Member newMember = Member.builder()
                                 .accountId(tossUser.getAccountId())
                                 .name(tossUser.getName())
                                 .phoneNumber(tossUser.getPhoneNumber())
                                 .ci(tossUser.getCi())
                                 .role(Role.USER) // 초기 권한 USER
                                 .build();
        return memberRepository.save(newMember);
    }

    // [중요] 토스 API 연동 부분 (현재는 테스트용 Mock)
    // 나중에 여기에 RestTemplate + mTLS 로직을 넣어야 합니다.
//    private TossUserInfo getTossUserInfo(String authCode) {
//        log.info("Toss Server로 authCode 전송: {}", authCode);
//
//        // TODO: 실제 토스 API 호출로 대체 예정
//        // 지금은 "임의의 유저"가 로그인했다고 가정합니다.
//        return new TossUserInfo(
//            "toss-id-" + UUID.randomUUID().toString().substring(0, 8), // 가짜 accountId
//            "테스트유저",
//            "010-1234-5678"
//        );
//    }
    private TossResponseDto.UserProfile getTossUserInfo(String authCode) {
        // A. 토큰 발급 요청 (generate-token)
        String tossAccessToken = requestTossAccessToken(authCode);

        // B. 유저 정보 요청 (login-me)
        return requestTossUserProfile(tossAccessToken);
    }

    // A. 토큰 발급 (POST /generate-token)
    private String requestTossAccessToken(String authCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // 최신 API는 보통 JSON 사용

        // Body 생성
        Map<String, String> body = new HashMap<>();
        body.put("authorizationCode", authCode); // 다이어그램 용어: 인가 코드
        body.put("client_id", tossClientId);     // 필요한 경우
        body.put("client_secret", tossClientSecret); // 필요한 경우

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<TossResponseDto.Token> response = restTemplate.postForEntity(
                tossTokenUrl, request, TossResponseDto.Token.class);

            return response.getBody().getAccessToken();

        } catch (HttpClientErrorException e) {
            log.error("토스 토큰 발급 실패: {}", e.getResponseBodyAsString());
            throw new RuntimeException("토스 로그인 실패 (토큰 발급)");
        }
    }

    // B. 유저 정보 조회 (GET /login-me)
    private TossResponseDto.UserProfile requestTossUserProfile(String tossAccessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tossAccessToken); // Authorization: Bearer {token}

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            // GET 요청은 exchange 메서드 사용
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

    // 내부에서만 쓸 DTO (토스에서 받아온 정보 묶음)
    private record TossUserInfo(String accountId, String name, String phoneNumber) {}
}
