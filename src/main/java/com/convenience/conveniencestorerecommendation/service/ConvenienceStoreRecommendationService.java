package com.convenience.conveniencestorerecommendation.service;

import com.convenience.conveniencestorerecommendation.domain.Direction;
import com.convenience.conveniencestorerecommendation.dto.OutputDto;
import com.convenience.conveniencestorerecommendation.dto.api.DocumentDto;
import com.convenience.conveniencestorerecommendation.dto.api.KakaoApiResponseDto;
import com.convenience.conveniencestorerecommendation.service.api.KakaoAddressSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.convenience.conveniencestorerecommendation.constant.BaseURL.KAKAO_DIRECTION_URL;
import static com.convenience.conveniencestorerecommendation.constant.BaseURL.KAKAO_ROAD_VIEW_URL;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConvenienceStoreRecommendationService {
    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;
    private final Base62Service base62Service;

    @Value("${convenience-store.recommendation.base.url}") private String baseUrl;

    public List<OutputDto> recommendConvenienceStoreList(String address) {
        // 사용자 주소 -> 위도, 경도
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        // validation
        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
            log.error("[ConvenienceStoreRecommendationService recommendConvenienceStoreList fail] Input address: {}", address);

            return Collections.emptyList();
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

        // 사용자 주소 반경 10km 이내의 최대 3개의 편의점 리스트
        List<Direction> directionList = directionService.buildDirectionListByCategoryApi(documentDto);

        return directionService.saveAll(directionList)
                .stream()
                .map(this::convertToOutputDto)
                .collect(Collectors.toList());
    }

    private OutputDto convertToOutputDto(Direction direction) {
        String params = String.join(",",
                direction.getTargetConvenienceStoreName(),
                String.valueOf(direction.getTargetLatitude()),
                String.valueOf(direction.getTargetLongitude())
        );

        String result = UriComponentsBuilder.fromHttpUrl(KAKAO_DIRECTION_URL.getUrl() + params).toUriString();

        return OutputDto.builder()
                .convenienceStoreName(direction.getTargetConvenienceStoreName())
                .convenienceStoreAddress(direction.getTargetAddress())
                .directionUrl(baseUrl + base62Service.encodeDirectionId(direction.getId()))
                .roadViewUrl(KAKAO_ROAD_VIEW_URL.getUrl() + direction.getTargetLatitude() + "," + direction.getTargetLongitude())
                .distance(String.format("%.2f km", direction.getDistance()))
                .build();
    }
}
