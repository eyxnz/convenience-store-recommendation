package com.convenience.conveniencestorerecommendation.service;

import com.convenience.conveniencestorerecommendation.domain.Direction;
import com.convenience.conveniencestorerecommendation.dto.api.DocumentDto;
import com.convenience.conveniencestorerecommendation.repository.DirectionRepository;
import com.convenience.conveniencestorerecommendation.service.api.KakaoCategorySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DirectionService { // 사용자 주소 반경 10km 이내의 최대 3개의 편의점 리스트
    private final KakaoCategorySearchService kakaoCategorySearchService;
    private final DirectionRepository directionRepository;

    private static final int MAX_SEARCH_COUNT = 3; // 약국 최대 검색 개수

    @Transactional
    public List<Direction> saveAll(List<Direction> directionList) {
        if (CollectionUtils.isEmpty(directionList)) {
            return Collections.emptyList();
        }

        return directionRepository.saveAll(directionList);
    }

    public List<Direction> buildDirectionListByCategoryApi(DocumentDto inputDocumentDto) {
        // validation
        if (Objects.isNull(inputDocumentDto)) {
            return Collections.emptyList();
        }

        return kakaoCategorySearchService.requestConvenienceStoreCategorySearch(
                        inputDocumentDto.getLatitude(),
                        inputDocumentDto.getLongitude(),
                        inputDocumentDto.getDistance()
                )
                .getDocumentList()
                .stream()
                .map(resultDocumentDto -> Direction.builder()
                        .inputAddress(inputDocumentDto.getAddressName())
                        .inputLatitude(inputDocumentDto.getLatitude())
                        .inputLongitude(inputDocumentDto.getLongitude())
                        .targetConvenienceStoreName(resultDocumentDto.getPlaceName())
                        .targetAddress(resultDocumentDto.getAddressName())
                        .targetLatitude(resultDocumentDto.getLatitude())
                        .targetLongitude(resultDocumentDto.getLongitude())
                        .distance(resultDocumentDto.getDistance() * 0.001) // km 단위
                        .build())
                .limit(MAX_SEARCH_COUNT)
                .collect(Collectors.toList());
    }
}
