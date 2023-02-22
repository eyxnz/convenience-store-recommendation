package com.convenience.conveniencestorerecommendation.constant;

import lombok.Getter;

public enum BaseURL {
    KAKAO_LOCAL_SEARCH_ADDRESS_URL("https://dapi.kakao.com/v2/local/search/address.json");

    @Getter private final String url;

    BaseURL(String url) {
        this.url = url;
    }
}
