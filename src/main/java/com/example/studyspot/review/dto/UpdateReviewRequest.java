package com.example.studyspot.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UpdateReviewRequest(
        @Positive
        @DecimalMin("0.0")
        @DecimalMax("5.0")
        double starRating,
        @NotBlank String content
) {
}
