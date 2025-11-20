package com.example.studyspot.cafe.dto;

import com.example.studyspot.cafe.domain.model.Image;

public record ImageDTO(
        String imageUrl,
        Long sequence
) {
    public static ImageDTO from (Image image) {
        return new ImageDTO(image.getImageUrl().getValue(), image.getSequence());
    }
}
