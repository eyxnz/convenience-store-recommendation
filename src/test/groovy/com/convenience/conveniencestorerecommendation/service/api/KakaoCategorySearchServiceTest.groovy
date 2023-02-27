package com.convenience.conveniencestorerecommendation.service.api

import com.convenience.conveniencestorerecommendation.AbstractIntegrationContainerBaseTest
import org.springframework.beans.factory.annotation.Autowired

class KakaoCategorySearchServiceTest extends AbstractIntegrationContainerBaseTest {
    @Autowired private KakaoCategorySearchService kakaoCategorySearchService

    def "requestConvenienceStoreCategorySearch - 위도, 경도, 반경거리가 valid 하다면, 정상적으로 document 를 반환한다."() {
        given:
        def longitude = 127.029052
        def latitude = 37.60894036
        def radius = 10

        when:
        def result = kakaoCategorySearchService.requestConvenienceStoreCategorySearch(latitude, longitude, radius)

        then:
        result.documentList.size() > 0
        result.metaDto.totalCount > 0
        result.documentList.get(0).addressName != null
    }
}
