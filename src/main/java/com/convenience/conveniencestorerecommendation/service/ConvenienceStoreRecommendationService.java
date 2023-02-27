package com.convenience.conveniencestorerecommendation.service;

import com.convenience.conveniencestorerecommendation.domain.Direction;
import com.convenience.conveniencestorerecommendation.dto.OutputDto;
import com.convenience.conveniencestorerecommendation.dto.api.DocumentDto;
import com.convenience.conveniencestorerecommendation.dto.api.KakaoApiResponseDto;
import com.convenience.conveniencestorerecommendation.service.api.KakaoAddressSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConvenienceStoreRecommendationService {
    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

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
        // TODO : 화면 구현할 때 directionUrl, roadViewUrl 에 알맞는 데이터를 넣을 예정
        return OutputDto.builder()
                .convenienceStoreName(direction.getTargetConvenienceStoreName())
                .convenienceStoreAddress(direction.getTargetAddress())
                .directionUrl("지도 URL")
                .roadViewUrl("로드뷰 URL")
                .distance(String.format("%.2f km", direction.getDistance()))
                .build();
    }
}
