package com.toss.foodtruck.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        // 1. 보안 스키마 설정 (JWT 설정)
        String jwtSchemeName = "bearerAuth";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        Components components = new Components()
            .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                .name(jwtSchemeName)
                .type(SecurityScheme.Type.HTTP) // HTTP 방식
                .scheme("bearer")             // Bearer 토큰
                .bearerFormat("JWT"));        // JWT 형식

        // 2. OpenAPI 객체 반환
        return new OpenAPI()
            .components(components)
            .addSecurityItem(securityRequirement) // 모든 API에 보안 적용
            .info(new Info()
                .title("Food Truck API")
                .description("앱인토스 푸드트럭 서비스 API 명세서")
                .version("v1.0.0"));
    }
}
