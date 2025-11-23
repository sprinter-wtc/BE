package com.example.studyspot.cafe.dto.response;

import com.example.studyspot.cafe.dto.*;
import lombok.Builder;

import java.util.List;

@Builder
public record CafeDetailsResponse(
        Long id,
        String name,
        String category,
        double[] location,
        String[] purpose,
        List<BusinessHourDTO> businessHourList,
        Long limitTime,
        String phoneNumber,
        TagDTO tags,
        List<MenuDTO> menuList,
        List<ImageDTO> imageList
) {
    public static CafeDetailsResponse from(CafeDetailsDTO dto) {
        return CafeDetailsResponse.builder()
                .id(dto.id())
                .name(dto.name())
                .category(dto.category())
                .location(dto.location())
                .purpose(dto.purpose())
                .businessHourList(dto.businessHourList())
                .limitTime(dto.limitTime())
                .phoneNumber(dto.phoneNumber())
                .tags(dto.tags())
                .menuList(dto.menuList())
                .imageList(dto.imageList())
                .build();
    }
}
