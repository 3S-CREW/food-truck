package com.toss.foodtruck.global.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * [외부 API 통신 설정]
 * 스프링 부트 서버가 다른 서버(예: 토스 API 서버)에게
 * HTTP 요청(GET, POST)을 보낼 때 사용하는 도구를 설정하는 클래스입니다.
 */
@Configuration
public class RestTemplateConfig {
    /**
     * RestTemplate 빈(Bean) 등록
     * * [역할]
     * 1. HTTP 클라이언트 생성: 자바 코드로 외부 URL을 호출할 수 있게 해줍니다.
     * 2. 안전장치(Timeout) 설정: 상대방 서버가 응답이 없을 때, 무한정 기다리지 않고 끊어버립니다.
     * * [주요 사용처]
     * - AuthService: 프론트에서 받은 토스 'authCode'가 진짜인지 토스 서버에 물어볼 때
     * - (추후) PaymentService: 결제 승인/취소 API를 호출할 때
     */

    /**
     * [Spring Boot 3.4+ 대응 버전]
     * 빌더의 setConnectTimeout 대신 requestFactory를 직접 설정합니다.
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
            .requestFactory(() -> {
                // 기본 Java HTTP 클라이언트 사용 (SimpleClientHttpRequestFactory)
                SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

                // 연결 타임아웃 설정 (5초 = 5000ms)
                factory.setConnectTimeout(5000);

                // 읽기 타임아웃 설정 (5초 = 5000ms)
                factory.setReadTimeout(5000);

                return factory;
            })
            .build();
    }
}