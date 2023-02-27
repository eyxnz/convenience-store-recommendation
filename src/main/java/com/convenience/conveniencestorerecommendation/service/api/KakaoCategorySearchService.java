package com.convenience.conveniencestorerecommendation.service.api;

import com.convenience.conveniencestorerecommendation.dto.api.KakaoApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoCategorySearchService {
    private final RestTemplate restTemplate;
    private final KakaoUriBuilderService kakaoUriBuilderService;

    @Value("${kakao.rest.api.key}") private String kakaoRestApiKey;

    private static final String CONVENIENCE_STORE_CATEGORY = "CS2"; // 편의점 카테고리 코드

    @Retryable(
            value = {RuntimeException.class},
            maxAttempts = 2,
            backoff = @Backoff(delay = 2000)
    )
    public KakaoApiResponseDto requestConvenienceStoreCategorySearch(double latitude, double longitude, double radius) {
        // URI 생성
        URI uri = kakaoUriBuilderService.buildUriByCategorySearch(
                latitude,
                longitude,
                radius,
                CONVENIENCE_STORE_CATEGORY
        );

        // Header 세팅
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK "+ kakaoRestApiKey);

        HttpEntity httpEntity = new HttpEntity<>(headers);

        // 카카오 API 호출
        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                KakaoApiResponseDto.class
        ).getBody();
    }
}
