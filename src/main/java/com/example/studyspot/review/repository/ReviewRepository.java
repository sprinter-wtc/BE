package com.example.studyspot.review.repository;

import com.example.studyspot.review.domain.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);

    Optional<Review> findById(Long reviewId);

    List<Review> findAllByCafeId(Long cafeId);

    public void delete(Review review);

    void clear();
}
