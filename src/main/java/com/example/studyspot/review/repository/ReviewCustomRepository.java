package com.example.studyspot.review.repository;

import com.example.studyspot.review.dto.ReviewDTO;

import java.util.List;

public interface ReviewCustomRepository {
    List<ReviewDTO> findBestReviewsByCafeId(Long cafeId);
}
