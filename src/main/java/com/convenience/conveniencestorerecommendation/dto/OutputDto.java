package com.convenience.conveniencestorerecommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutputDto {
    private String convenienceStoreName;    // 편의점 이름
    private String convenienceStoreAddress; // 편의점 주소
    private String directionUrl;            // 길안내 url
    private String roadViewUrl;             // 로드뷰 url
    private String distance;                // 고객 주소와 편의점 주소의 거리
}
