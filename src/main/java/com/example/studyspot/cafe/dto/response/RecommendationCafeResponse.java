package com.example.studyspot.cafe.dto.response;

import com.example.studyspot.cafe.dto.CafeSimpleInfoDTO;

import java.util.List;

public record RecommendationCafeResponse(
        List<CafeSimpleInfoDTO> recommendationCafes
) {
    public static RecommendationCafeResponse from (List<CafeSimpleInfoDTO> recommendationCafes) {
        return new RecommendationCafeResponse(recommendationCafes);
    }
}
