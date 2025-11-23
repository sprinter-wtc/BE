package com.example.studyspot.cafe.dto;

import com.example.studyspot.cafe.domain.model.Tag;
import lombok.Builder;

@Builder
public record TagDTO(
        String lightningLevel,
        String noiseLevel,
        String parkingAvailability,
        String petFriendly,
        String powerOutletLevel,
        String stayDurationPolicy,
        String surroundingEnvironment,
        String transportLevel
) {
    public static TagDTO from (Tag tag){
        return TagDTO.builder()
                .lightningLevel(tag.getLightningLevel().getValue())
                .noiseLevel(tag.getNoiseLevel().getValue())
                .parkingAvailability(tag.getParkingAvailability().getValue())
                .petFriendly(tag.getPetFriendly().getValue())
                .powerOutletLevel(tag.getPowerOutletLevel().getValue())
                .stayDurationPolicy(tag.getStayDurationPolicy().getValue())
                .surroundingEnvironment(tag.getSurroundingEnvironment().getValue())
                .transportLevel(tag.getTransportLevel().getValue())
                .build();
    }
}
