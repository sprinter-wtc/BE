package com.example.studyspot.cafe.dto;

import com.example.studyspot.cafe.domain.enums.Purpose;
import com.example.studyspot.cafe.domain.model.*;
import lombok.Builder;

import java.util.List;

@Builder
public record CafeDetailsDTO (
        Long id,
        String name,
        String category,
        double[] location,
        String[] purpose,
        Long limitTime,
        String phoneNumber,
        TagDTO tags,
        List<MenuDTO> menuList,
        List<ImageDTO> imageList
) {
    public static CafeDetailsDTO of(Cafe cafe, List<Menu> menus, List<BusinessHour> businessHours, List<Image> images) {
        String[] purpose = cafe.getPurposes()
                .stream()
                .map(Purpose::getValue)
                .toArray(String[]::new);

        TagDTO tags = TagDTO.from(cafe.getTags());

        List<MenuDTO> menuList = menus
                .stream()
                .map(MenuDTO::from)
                .toList();

        List<ImageDTO> imageList = images
                .stream()
                .map(ImageDTO::from)
                .toList();

        return CafeDetailsDTO.builder()
                .id(cafe.getId())
                .name(cafe.getName().value())
                .category(cafe.getCategory().getValue())
                .location(cafe.getLocation().toCoordinates())
                .purpose(purpose)
                .limitTime(cafe.getLimitTime().value())
                .phoneNumber((cafe.getPhoneNumber().value()))
                .tags(tags)
                .menuList(menuList)
                .imageList(imageList)
                .build();
    }
}
