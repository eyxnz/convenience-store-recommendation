package com.convenience.conveniencestorerecommendation.service.api;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.convenience.conveniencestorerecommendation.constant.BaseURL.KAKAO_LOCAL_CATEGORY_SEARCH_URL;
import static com.convenience.conveniencestorerecommendation.constant.BaseURL.KAKAO_LOCAL_SEARCH_ADDRESS_URL;

@Service
public class KakaoUriBuilderService {
    // 주소 검색하기 URI 만들기
    public URI buildUriByAddressSearch(String address) {
        return UriComponentsBuilder
                .fromHttpUrl(KAKAO_LOCAL_SEARCH_ADDRESS_URL.getUrl())
                .queryParam("query", address)
                .build()
                .encode()
                .toUri();
    }

    // 카테고리로 장소 검색하기 URI 만들기
    public URI buildUriByCategorySearch(double latitude, double longitude, double radius, String category) {
        // 카카오 API 는 미터 단위를 사용
        double meterRadius = radius * 1000;

        return UriComponentsBuilder
                .fromHttpUrl(KAKAO_LOCAL_CATEGORY_SEARCH_URL.getUrl())
                .queryParam("category_group_code", category)
                .queryParam("x", longitude)
                .queryParam("y", latitude)
                .queryParam("radius", meterRadius)
                .queryParam("sort","distance")
                .build()
                .encode()
                .toUri();
    }
}
