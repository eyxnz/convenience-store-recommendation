package com.convenience.conveniencestorerecommendation.service.api

import com.convenience.conveniencestorerecommendation.AbstractIntegrationContainerBaseTest
import org.springframework.beans.factory.annotation.Autowired

class KakaoAddressSearchServiceTest extends AbstractIntegrationContainerBaseTest {
    @Autowired private KakaoAddressSearchService kakaoAddressSearchService

    def "requestAddressSearch - address 파라미터 값이 null이면, null을 리턴한다."() {
        given:
        String address = null

        when:
        def result = kakaoAddressSearchService.requestAddressSearch(address)

        then:
        result == null
    }

    def "requestAddressSearch - 주소값이 valid 하다면, 정상적으로 document 를 반환한다."() {
        given:
        def address = "서울 성북구 종암로 10길"

        when:
        def result = kakaoAddressSearchService.requestAddressSearch(address)

        then:
        result.documentList.size() > 0
        result.metaDto.totalCount > 0
        result.documentList.get(0).addressName != null
    }
}
