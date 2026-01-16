package com.toss.foodtruck.domain.auth.controller;

import com.toss.foodtruck.domain.auth.dto.LoginRequestDto;
import com.toss.foodtruck.domain.auth.dto.TokenResponseDto;
import com.toss.foodtruck.global.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "인증/로그인 관련 API")
public interface AuthController {
    @Operation(summary = "토스 로그인", description = "토스 앱에서 받은 authCode를 이용해 로그인/회원가입을 진행합니다.")
    public ResponseEntity<ResultResponse<TokenResponseDto>> login(@RequestBody LoginRequestDto request);
}
