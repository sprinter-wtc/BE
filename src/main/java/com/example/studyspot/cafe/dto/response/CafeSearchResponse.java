package com.example.studyspot.cafe.dto.response;

import com.example.studyspot.cafe.dto.CafeSimpleInfoDTO;

import java.util.List;

public record CafeSearchResponse(
        List<CafeSimpleInfoDTO> cafes
) {
    public static CafeSearchResponse from (List<CafeSimpleInfoDTO> cafes) {
        return new CafeSearchResponse(cafes);
    }
}
