package com.example.studyspot.cafe.dto;

import com.example.studyspot.cafe.domain.model.Menu;
import lombok.Builder;

@Builder
public record MenuDTO(
        String name,
        Long price,
        String description,
        String imageUrl
) {
    public static MenuDTO from (Menu menu) {
        return MenuDTO.builder()
                .name(menu.getMenuName().getValue())
                .price(menu.getPrice().getValue())
                .description(menu.getDescription().getValue())
                .imageUrl(menu.getImageUrl().getValue())
                .build();
    }
}
