package com.convenience.conveniencestorerecommendation.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoApiResponseDto {
    // @JsonProperty : json 으로 응답을 받을 때, field 랑 매칭시켜주기 위해 사용
    @JsonProperty("meta") private MetaDto metaDto;
    @JsonProperty("documents") private List<DocumentDto> documentList;
}
