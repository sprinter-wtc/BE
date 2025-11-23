package com.example.studyspot.cafe.dto;

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
        List<BusinessHourDTO> businessHourList,
        Long limitTime,
        String phoneNumber,
        TagDTO tags,
        List<MenuDTO> menuList,
        List<ImageDTO> imageList
) {
    public static CafeDetailsDTO of(Cafe cafe, List<Menu> menus, List<BusinessHour> businessHours, List<Image> images, List<PurposeType> purposeType) {
        String[] purpose = purposeType
                .stream()
                .map(type-> type.getPurpose().getValue())
                .toArray(String[]::new);

        TagDTO tags = TagDTO.from(cafe.getTags());

        List<BusinessHourDTO> businessHourList = businessHours
                .stream()
                .map(BusinessHourDTO::from)
                .toList();

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
                .name(cafe.getName().getValue())
                .category(cafe.getCategory().getValue())
                .location(cafe.getLocation().toCoordinates())
                .purpose(purpose)
                .businessHourList(businessHourList)
                .limitTime(cafe.getLimitTime().getValue())
                .phoneNumber((cafe.getPhoneNumber().getValue()))
                .tags(tags)
                .menuList(menuList)
                .imageList(imageList)
                .build();
    }
}
