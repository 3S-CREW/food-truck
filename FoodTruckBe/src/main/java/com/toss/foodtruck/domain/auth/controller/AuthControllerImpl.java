package com.toss.foodtruck.domain.auth.controller;

import com.toss.foodtruck.domain.auth.dto.LoginRequestDto;
import com.toss.foodtruck.domain.auth.dto.TokenResponseDto;
import com.toss.foodtruck.domain.auth.service.AuthService;
import com.toss.foodtruck.global.response.ResultCode;
import com.toss.foodtruck.global.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<ResultResponse<TokenResponseDto>> login(@RequestBody LoginRequestDto request) {
        TokenResponseDto tokenResponse = authService.login(request);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.LOGIN_SUCCESS, tokenResponse));
    }
}
