package com.example.studyspot.review.dto;

import java.sql.Timestamp;

public record ReviewDTOWithEpoch (
        Double starRating,
        String name,
        long createdAt,
        String content,
        String imageUrl
){
    public static ReviewDTOWithEpoch from (ReviewDTO dto) {
        return new ReviewDTOWithEpoch(
                dto.starRating(),
                dto.name(),
                Timestamp.valueOf(dto.createdAt()).getTime(),
                dto.content(),
                dto.imageUrl()
        );
    }
}
