package com.example.studyspot.review.dto;

import java.util.List;

public record BestReviewsDTO (
        Double averageStarRating,
        Long reviewCount,
        List<ReviewDTOWithEpoch> reviews
) {
    public static BestReviewsDTO from (Double averageStarRating, Long reviewCount, List<ReviewDTO> reviewList) {
        List<ReviewDTOWithEpoch> reviews = reviewList
                .stream()
                .map(ReviewDTOWithEpoch::from)
                .toList();

        return new BestReviewsDTO(averageStarRating, reviewCount, reviews);

    }
}
