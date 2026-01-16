package com.toss.foodtruck.global.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;

@Slf4j
@Configuration
public class TossConfig {

    @Value("${toss.cert.path}")
    private String certPath;

    @Value("${toss.cert.password}")
    private String certPassword;

    @Value("${toss.cert.type}")
    private String certType; // "PKCS12"

    @Bean(name = "tossRestTemplate")
    public RestTemplate tossRestTemplate(ResourceLoader resourceLoader) {
        try {
            // 1. KeyStore 로드 (.p12 파일 읽기)
            KeyStore keyStore = KeyStore.getInstance(certType);
            Resource resource = resourceLoader.getResource(certPath);

            try (InputStream inputStream = resource.getInputStream()) {
                keyStore.load(inputStream, certPassword.toCharArray());
            }

            // 2. SSLContext 생성 (내 인증서 탑재)
            // loadKeyMaterial: 나를 증명할 인증서(Keystore)를 등록
            // loadTrustMaterial: 상대방(토스)을 신뢰할지 설정 (null이면 시스템 기본 신뢰 저장소 사용)
            SSLContext sslContext = SSLContextBuilder.create()
                                                     .loadKeyMaterial(keyStore, certPassword.toCharArray())
                                                     .build();

            // 3. SSL Factory 생성
            SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                                                                                           .setSslContext(sslContext)
                                                                                           .build();

            // 4. Connection Manager에 SSL Factory 연결
            HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                                                                                      .setSSLSocketFactory(sslSocketFactory)
                                                                                      .build();

            // 5. HttpClient 생성
            CloseableHttpClient httpClient = HttpClients.custom()
                                                        .setConnectionManager(cm)
                                                        .build();

            // 6. RestTemplate 생성 및 반환
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
            return new RestTemplate(factory);

        } catch (Exception e) {
            log.error("Toss mTLS 인증서 로드 실패! 설정 파일과 비밀번호를 확인하세요.", e);
            throw new RuntimeException("Toss RestTemplate 생성 실패", e);
        }
    }
}