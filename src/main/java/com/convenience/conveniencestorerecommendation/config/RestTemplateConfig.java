package com.convenience.conveniencestorerecommendation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() { // 카카오 API 호출을 위해 빈으로 등록
        return new RestTemplate();
    }
}
