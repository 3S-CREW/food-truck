package com.toss.foodtruck.global.config;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * [외부 API 통신 설정]
 * 스프링 부트 서버가 다른 서버(예: 토스 API 서버)에게
 * HTTP 요청(GET, POST)을 보낼 때 사용하는 도구를 설정하는 클래스입니다.
 */
@Configuration
public class RestTemplateConfig {
    
    // mTLS 미적용 버전. 타임아웃만 임시 설정해둠
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
    
    // mTLS 인증서 적용 버전
//    @Value("${toss.cert.path}")
//    private Resource certFile; // 인증서 파일
//
//    @Value("${toss.cert.password}")
//    private String certPassword; // 인증서 비번
//
//    @Value("${toss.cert.type}")
//    private String certType; // PKCS12
//    
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
//        // 1. 인증서 파일 로드 (KeyStore 생성)
//        KeyStore clientStore = KeyStore.getInstance(certType);
//        try (InputStream inputStream = certFile.getInputStream()) {
//            clientStore.load(inputStream, certPassword.toCharArray());
//        }
//
//        // 2. SSL Context 생성 (우리 서버의 신분증 탑재. mTLS)
//        SSLContext sslContext = SSLContextBuilder.create()
//             .loadKeyMaterial(clientStore, certPassword.toCharArray()) // 내 인증서 로드
//             // .loadTrustMaterial(...) // 필요하다면 토스 서버의 인증서도 신뢰하도록 설정
//             .build();
//
//        // 3. SSL 소켓 팩토리 생성
//        SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
//            .setSslContext(sslContext)
//            .build();
//
//        // 4. 타임아웃 설정 (ConnectionConfig 사용)
//        ConnectionConfig connectionConfig = ConnectionConfig.custom()
//            .setConnectTimeout(Timeout.ofMilliseconds(5000)) // 연결 타임아웃
//            .setSocketTimeout(Timeout.ofMilliseconds(5000)) // 읽기 타임아웃
//            .build();
//
//        // 5. Connection Manager 생성 및 SSL 설정 연결
//        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
//            .setSSLSocketFactory(sslSocketFactory)
//            .setDefaultConnectionConfig(connectionConfig) // 타임아웃 설정 주입
//            .build();
//
//        // 6. HttpClient 생성 (Apache HttpClient 5 사용)
//        CloseableHttpClient httpClient = HttpClients.custom()
//            .setConnectionManager(cm)
//            .build();
//
//        // 7. RestTemplate에 탑재
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpClient);
//
//        return builder
//            .requestFactory(() -> requestFactory)
//            .build();
//    }
}