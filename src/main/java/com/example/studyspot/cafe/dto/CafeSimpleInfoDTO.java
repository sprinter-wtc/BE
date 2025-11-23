package com.example.studyspot.cafe.dto;

import com.example.studyspot.cafe.domain.model.BusinessHour;
import com.example.studyspot.cafe.domain.model.Cafe;
import com.example.studyspot.cafe.domain.model.Image;
import com.example.studyspot.cafe.domain.model.Tag;
import lombok.Builder;

import java.time.LocalTime;

@Builder
public record CafeSimpleInfoDTO(
        Long id,
        String name,
        Double averageStarRating,
        Boolean isWork,
        LocalTime startingTime,
        LocalTime closingTime,
        String category,
        String[] tags,
        String imageUrl,
        String address
) {
    public static CafeSimpleInfoDTO from (Cafe cafe,
                                          BusinessHour businessHour,
                                          Boolean isWork,
                                          Image representativeImage) {
        Tag tag = cafe.getTags();
        String[] tags = new String[]{
                tag.getPowerOutletLevel().getValue(),
                tag.getParkingAvailability().getValue()
        };

        return CafeSimpleInfoDTO.builder()
                .id(cafe.getId())
                .name(cafe.getName().getValue())
                .averageStarRating(4.5) //이 부분은 리뷰와 합쳐진 후 인자로 받게끔 리팩토링
                .isWork(isWork)
                .startingTime(businessHour.getStartAt())
                .closingTime(businessHour.getEnd_at())
                .category(cafe.getCategory().getValue())
                .tags(tags)
                .imageUrl(representativeImage.getImageUrl().getValue())
                .address(cafe.getAddress().getValue())
                .build();
    }
}
