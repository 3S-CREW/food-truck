package com.toss.foodtruck.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TokenResponseDto {
    private String grantType; // "Bearer"
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
}
