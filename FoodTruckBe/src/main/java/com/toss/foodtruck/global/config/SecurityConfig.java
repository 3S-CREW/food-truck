package com.toss.foodtruck.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화 (REST API는 보통 비활성화)
            .formLogin(AbstractHttpConfigurer::disable) // 기본 로그인 폼 비활성화
            .httpBasic(AbstractHttpConfigurer::disable) // HTTP Basic 인증 비활성화
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // 개발 초기: 모든 요청 허용 (추후 변경 예정)
            );

        return http.build();
    }
}