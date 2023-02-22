package com.convenience.conveniencestorerecommendation.service.api;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
}
