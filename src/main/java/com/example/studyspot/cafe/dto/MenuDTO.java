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
                .name(menu.getMenuName().value())
                .price(menu.getPrice().value())
                .description(menu.getDescription().value())
                .imageUrl(menu.getImageUrl().value())
                .build();
    }
}
