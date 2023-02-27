package com.convenience.conveniencestorerecommendation.constant;

import lombok.Getter;

public enum BaseURL {
    KAKAO_LOCAL_SEARCH_ADDRESS_URL("https://dapi.kakao.com/v2/local/search/address.json"),
    KAKAO_LOCAL_CATEGORY_SEARCH_URL("https://dapi.kakao.com/v2/local/search/category.json"),
    KAKAO_ROAD_VIEW_URL("https://map.kakao.com/link/roadview/"),
    KAKAO_DIRECTION_URL("https://map.kakao.com/link/map/")
    ;

    @Getter private final String url;

    BaseURL(String url) {
        this.url = url;
    }
}
