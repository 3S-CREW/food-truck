package com.toss.foodtruck.domain.auth.service;

import com.toss.foodtruck.domain.auth.dto.LoginRequestDto;
import com.toss.foodtruck.domain.auth.dto.TokenResponseDto;
import com.toss.foodtruck.domain.entity.Member;
import com.toss.foodtruck.domain.repository.MemberRepository;
import com.toss.foodtruck.global.enums.Role;
import com.toss.foodtruck.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;


    // 실제 로그인 로직
    @Transactional
    public TokenResponseDto login(LoginRequestDto request) {

        // 1. 프론트에서 받은 authCode로 토스 서버에서 유저 정보(accountId, 이름 등) 가져오기
        // (지금은 가짜 데이터를 반환하도록 구현됨 - 하단 메서드 참고)
        TossUserInfo tossUser = getTossUserInfo(request.getAuthCode());

        // 2. DB 조회: 이미 가입된 유저인가?
        Member member = memberRepository.findByAccountId(tossUser.accountId)
                                        .orElseGet(() -> signup(tossUser)); // 없으면 회원가입 진행

        // 3. 토스 정보가 변경되었을 수 있으니 업데이트 (이름, 전화번호 등)
        member.updateInfo(tossUser.name, tossUser.phoneNumber);

        // 4. JWT 토큰 발급
        String accessToken = jwtTokenProvider.createAccessToken(member.getId(), member.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());

        // 5. Redis에 Refresh Token 저장 (유효기간 설정)
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
    private Member signup(TossUserInfo tossUser) {
        Member newMember = Member.builder()
                                 .accountId(tossUser.accountId)
                                 .name(tossUser.name)
                                 .phoneNumber(tossUser.phoneNumber)
                                 .role(Role.USER) // 기본은 손님으로 가입
                                 .build();

        return memberRepository.save(newMember);
    }

    // [중요] 토스 API 연동 부분 (현재는 테스트용 Mock)
    // 나중에 여기에 RestTemplate + mTLS 로직을 넣어야 합니다.
    private TossUserInfo getTossUserInfo(String authCode) {
        log.info("Toss Server로 authCode 전송: {}", authCode);

        // TODO: 실제 토스 API 호출로 대체 예정
        // 지금은 "임의의 유저"가 로그인했다고 가정합니다.
        return new TossUserInfo(
            "toss-id-" + UUID.randomUUID().toString().substring(0, 8), // 가짜 accountId
            "테스트유저",
            "010-1234-5678"
        );
    }

    // 내부에서만 쓸 DTO (토스에서 받아온 정보 묶음)
    private record TossUserInfo(String accountId, String name, String phoneNumber) {}
}
