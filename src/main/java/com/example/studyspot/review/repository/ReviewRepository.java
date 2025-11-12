package com.example.studyspot.review.repository;

import com.example.studyspot.review.domain.model.Review;

import java.util.List;

public interface ReviewRepository {
    Review save(Review review);

    Review findById(Long reviewId);

    List<Review> findAllByCafeId(Long cafeId);

    void clear();
}
