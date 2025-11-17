package com.example.studyspot.review.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateReviewRequest(
        @NotBlank String content
) {
}
