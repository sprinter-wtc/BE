package com.example.studyspot.review.dto;

import java.util.List;

public record BestReviewResponse(
        Double averageStarRating,
        Long reviewCount,
        List<ReviewDTOWithEpoch> reviews
) {
    public static BestReviewResponse from(BestReviewsDTO bestReviewsDTO) {
        return new BestReviewResponse(
                bestReviewsDTO.averageStarRating(),
                bestReviewsDTO.reviewCount(),
                bestReviewsDTO.reviews()
        );
    }
}
