package com.toss.foodtruck.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toss.foodtruck.global.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Getter
@Component
public class JwtTokenProvider {

    private final Key key;
    private final ObjectMapper objectMapper;
    private final long tokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    public JwtTokenProvider(
        @Value("${jwt.secret}") String secretKey,
        @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds,
        ObjectMapper objectMapper) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
        this.refreshTokenValidityInMilliseconds = tokenValidityInSeconds * 14 * 24 * 60 * 1000; // 2주
        this.objectMapper = objectMapper;
    }

    // Access Token 생성
    public String createAccessToken(Long userId, Role role) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        claims.put("role", role.name()); // Enum -> String 저장

        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMilliseconds);

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(validity)
                   .signWith(key, SignatureAlgorithm.HS256)
                   .compact();
    }

    // Refresh Token 생성
    public String createRefreshToken(Long userId) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));

        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInMilliseconds);

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(validity)
                   .signWith(key, SignatureAlgorithm.HS256)
                   .compact();
    }

    // 토큰 검증 (Filter에서 예외를 처리하므로 여기서는 던짐)
    public boolean validateToken(String token) {
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token);
        return true;
    }

    // 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        String userId = claims.getSubject();
        String roleStr = claims.get("role", String.class);

        // Role이 없는 경우(Refresh Token 등)에 대한 방어 로직 필요 시 추가
        String roleAuthority = (roleStr != null) ? Role.valueOf(roleStr).getKey() : Role.USER.getKey();

        return new UsernamePasswordAuthenticationToken(userId, null,
            Collections.singletonList(new SimpleGrantedAuthority(roleAuthority)));
    }

    // 토큰에서 Claims 추출
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    // 토큰에서 UserId 추출
    public Long getUserId(String token) {
        String subject = getClaims(token).getSubject();
        return Long.valueOf(subject);
    }
}