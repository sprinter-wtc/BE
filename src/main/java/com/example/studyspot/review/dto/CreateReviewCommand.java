package com.example.studyspot.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateReviewCommand (
        long cafeId,
        double starRating,
        String content
){
}
