package com.toss.foodtruck.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class TossResponseDto {

    // 1. 토큰 발급 응답 (generate-token)
    @Getter
    @NoArgsConstructor
    @ToString
    public static class Token {
        @JsonProperty("accessToken")
        private String accessToken;

        @JsonProperty("tokenType")
        private String tokenType;

        @JsonProperty("expiresIn")
        private Integer expiresIn;
    }

    // 2. 유저 정보 응답 (login-me)
    @Getter
    @NoArgsConstructor
    @ToString
    public static class UserProfile {
        @JsonProperty("accountId") // 토스 문서에 따라 필드명 일치시켜야 함
        private String accountId;

        @JsonProperty("name")
        private String name;

        @JsonProperty("phoneNumber")
        private String phoneNumber;

        @JsonProperty("ci")
        private String ci;
    }
}
