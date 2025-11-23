package com.example.studyspot.review.dto;

import java.time.LocalDateTime;

public record ReviewDTO(
        Double starRating,
        String name,
        LocalDateTime createdAt,
        String content,
        String imageUrl
) {
}
