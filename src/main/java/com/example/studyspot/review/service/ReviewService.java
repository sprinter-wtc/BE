package com.example.studyspot.review.service;

import com.example.studyspot.review.domain.model.Review;
import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {

    //리뷰 등록
    ReviewResponse create(CreateReviewCommand command);

    //리뷰 조회
    List<Review> findAllByCafeId(Long cafeId);
}
