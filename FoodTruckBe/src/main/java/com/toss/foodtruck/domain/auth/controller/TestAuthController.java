package com.toss.foodtruck.domain.auth.controller;

import com.toss.foodtruck.domain.auth.dto.TokenResponseDto;
import com.toss.foodtruck.domain.member.entity.Member;
import com.toss.foodtruck.domain.member.repository.MemberRepository;
import com.toss.foodtruck.global.enums.Role;
import com.toss.foodtruck.global.jwt.JwtTokenProvider;
import com.toss.foodtruck.global.response.ResultCode;
import com.toss.foodtruck.global.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Tag(name = "Test-Auth", description = "[테스트용] 개발 전용 로그인 API (토스 연동 X)")
@Slf4j
@RestController
@RequestMapping("/api/v1/test/auth")
@RequiredArgsConstructor
public class TestAuthController {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    @Operation(summary = "일반 유저(USER)로 로그인", description = "테스트용 일반 회원 계정을 생성하고 토큰을 발급합니다.")
    @GetMapping("/login/user")
    public ResponseEntity<ResultResponse<TokenResponseDto>> loginAsUser() {
        return ResponseEntity.ok(ResultResponse.of(
            ResultCode.LOGIN_SUCCESS,
            createTestToken("TEST_USER_ID", "테스트유저", Role.USER)
        ));
    }

    @Operation(summary = "사장님(OWNER)으로 로그인", description = "테스트용 사장님 계정을 생성하고 토큰을 발급합니다.")
    @GetMapping("/login/owner")
    public ResponseEntity<ResultResponse<TokenResponseDto>> loginAsOwner() {
        return ResponseEntity.ok(ResultResponse.of(
            ResultCode.LOGIN_SUCCESS,
            createTestToken("TEST_OWNER_ID", "테스트사장님", Role.OWNER)
        ));
    }

    @Operation(summary = "관리자(ADMIN)로 로그인", description = "테스트용 관리자 계정을 생성하고 토큰을 발급합니다.")
    @GetMapping("/login/admin")
    public ResponseEntity<ResultResponse<TokenResponseDto>> loginAsAdmin() {
        return ResponseEntity.ok(ResultResponse.of(
            ResultCode.LOGIN_SUCCESS,
            createTestToken("TEST_ADMIN_ID", "테스트관리자", Role.ADMIN)
        ));
    }

    // 공통 로직: DB 조회/생성 -> 토큰 발급 -> Redis 저장
    @Transactional
    public TokenResponseDto createTestToken(String accountId, String name, Role role) {
        // 1. DB에 테스트 계정이 없으면 생성, 있으면 조회
        Member member = memberRepository.findByAccountId(accountId)
                                        .orElseGet(() -> memberRepository.save(Member.builder()
                                                                                     .accountId(accountId)
                                                                                     .name(name)
                                                                                     .phoneNumber("010-0000-0000")
                                                                                     .role(role)
                                                                                     .build()));

        // 권한이 바뀌었을 수도 있으니 강제 업데이트 (테스트 편의성)
        if (member.getRole() != role) {
            member.changeRole(role);
        }

        // 2. JWT 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(member.getId(), member.getRole());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());

        // 3. Redis에 Refresh Token 저장
        redisTemplate.opsForValue()
                     .set("RT:" + member.getId(), refreshToken,
                         jwtTokenProvider.getRefreshTokenValidityInMilliseconds(), TimeUnit.MILLISECONDS);

        log.info("[Test Login] 계정: {}, 권한: {}, 토큰 발급 완료", name, role);

        return TokenResponseDto.builder()
                               .grantType("Bearer")
                               .accessToken(accessToken)
                               .refreshToken(refreshToken)
                               .accessTokenExpiresIn(jwtTokenProvider.getTokenValidityInMilliseconds())
                               .build();
    }
}