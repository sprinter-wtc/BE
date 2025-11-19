package com.example.studyspot.review.service;

import com.example.studyspot.review.domain.model.Review;
import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.dto.CreateReviewResponse;
import com.example.studyspot.review.dto.ReviewResponse;
import com.example.studyspot.review.dto.UpdateReviewResponse;

import java.util.List;

public interface ReviewService {

    //리뷰 등록
    CreateReviewResponse createReview(CreateReviewCommand command);

    //리뷰 조회
    List<ReviewResponse> findAllByCafeId(Long cafeId);

    //리뷰 수정
    UpdateReviewResponse updateReview(Long reviewId, Double starRating, String content);

    //리뷰 삭제
    public void deleteReview(Long id);
}
