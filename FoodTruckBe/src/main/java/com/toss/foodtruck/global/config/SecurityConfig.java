package com.toss.foodtruck.global.config;

import com.toss.foodtruck.global.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화 (REST API는 보통 비활성화)
            .formLogin(AbstractHttpConfigurer::disable) // 기본 로그인 폼 비활성화
            .httpBasic(AbstractHttpConfigurer::disable) // HTTP Basic 인증 비활성화
            .authorizeHttpRequests(auth -> auth
                // [핵심 2] 인증 없이 접근 가능한 경로 명시
                .requestMatchers(
                    "/api/v1/auth/**",       // 실제 로그인
                    "/api/v1/test/auth/**",  // 테스트 로그인
                    "/swagger-ui/**",        // Swagger UI
                    "/v3/api-docs/**",       // Swagger Docs
                    "/swagger-resources/**"
                ).permitAll()

                // 그 외 모든 요청(가게 등록 등)은 인증 필수
                .anyRequest().authenticated()
            )
            // JWT 필터를 Security 필터 체인에 끼워 넣기
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}