package com.example.studyspot.review.service;

import com.example.studyspot.common.exception.StudySpotException;
import com.example.studyspot.review.domain.model.Review;
import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.dto.CreateReviewResponse;
import com.example.studyspot.review.dto.ReviewResponse;
import com.example.studyspot.review.dto.UpdateReviewResponse;
import com.example.studyspot.review.exception.ReviewErrorType;
import com.example.studyspot.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;


    @Override
    @Transactional
    public CreateReviewResponse createReview(CreateReviewCommand command) {
        //카페 존재 여부 검증 vo - 추후

        // Review Entity 생성
        Review review = Review.from(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                command
        );

        //db 저장
        Review stored = reviewRepository.save(review);

        //응답 dto 반환
        return new CreateReviewResponse(stored.getId());

    }

    @Override
    @Transactional
    public List<ReviewResponse> findAllByCafeId(Long cafeId) {

        //cafeId 존재 여부 검증 vo - 추후

        List<Review> reviews = reviewRepository.findAllByCafeId(cafeId);

        // - 추후
        // reviews 에 담긴 userId에 맞는
        // userName 과 userprofileImage를 가져와 dto에 넣기

        List<ReviewResponse> reviewsDto = reviews.stream()
                .map(r -> new ReviewResponse(
                        r.getStarRating(),
                        null, //user name 부분
                        r.getCreatedAt(),
                        r.getContent(),
                        null //user image 부분

                )).toList();

        return reviewsDto;
    }

    @Override
    @Transactional
    public UpdateReviewResponse updateReview(Long reviewId, Double starRating, String content) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new StudySpotException(ReviewErrorType.REVIEW_NOT_FOUND));

        review.updateContent(content);
        review.updateStarRating(starRating);
        //변경 감지(dirty checking)으로 자동 UPDATE
        //reviewRepository.update(review);

        UpdateReviewResponse updateReviewResponse = new UpdateReviewResponse(review.getId());

        return updateReviewResponse;
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new StudySpotException(ReviewErrorType.REVIEW_NOT_FOUND));

        reviewRepository.delete(review);
    }


}
