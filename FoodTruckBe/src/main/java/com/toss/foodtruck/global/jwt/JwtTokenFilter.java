package com.toss.foodtruck.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toss.foodtruck.global.exception.ErrorCode;
import com.toss.foodtruck.global.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {

        // 1. Request Header에서 토큰 추출
        String jwt = resolveToken(request);

        try {
            // 2. 토큰이 유효한지 검증
            if (StringUtils.hasText(jwt)) {

                // validateToken 메서드 내에서 만료 여부 등 검사 (예외 발생 시 catch로 이동)
                jwtTokenProvider.validateToken(jwt);

                // 3. 토큰이 유효하면 Authentication 객체를 만들어 SecurityContext에 저장
                Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("Valid token for user: {}", authentication.getName());
            }

            // 4. 다음 필터로 진행
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            // 5. 토큰 검증 중 에러 발생 시 처리
            log.error("JWT Token Error: {}", e.getMessage());
            handleJwtException(response, e);
        } finally {
            // 6. 요청 처리가 끝나면 SecurityContext 비우기 (Stateless 유지)
            SecurityContextHolder.clearContext();
        }
    }

    // 예외 응답 처리 (JSON 형태로 클라이언트에 반환)
    private void handleJwtException(HttpServletResponse response, Exception exception) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ErrorResponse errorResponse;

        // 만료된 토큰인 경우와 그 외 유효하지 않은 토큰인 경우 구분
        if (exception instanceof ExpiredJwtException) {
            log.warn("Token Expired Exception");
            errorResponse = ErrorResponse.of(ErrorCode.EXPIRED_ACCESS_TOKEN);
        } else {
            log.warn("Invalid Token Exception");
            errorResponse = ErrorResponse.of(ErrorCode.INVALID_TOKEN);
        }

        String jsonResponse = objectMapper.writerWithDefaultPrettyPrinter()
                                          .writeValueAsString(errorResponse);
        response.getOutputStream().write(jsonResponse.getBytes(StandardCharsets.UTF_8));
    }

    // Header에서 Bearer 토큰 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}