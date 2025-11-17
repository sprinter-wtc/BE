package com.example.studyspot.review.service;

import com.example.studyspot.review.domain.model.Review;
import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.dto.CreateReviewResponse;
import com.example.studyspot.review.dto.ReviewResponse;
import com.example.studyspot.review.dto.UpdateReviewResponse;
import com.example.studyspot.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;


    @Override
    public CreateReviewResponse create(CreateReviewCommand command) {
        //카페 존재 여부 검증 vo - 추후

        // Review Entity 생성
        Review review = new Review(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                command.cafeId(),
                command.starRating(),
                LocalDateTime.now(),
                command.content()
        );

        //db 저장
        Review stored = reviewRepository.save(review);

        //응답 dto 반환
        return new CreateReviewResponse(stored.getId());

    }

    @Override
    public List<ReviewResponse> findAllByCafeId(Long cafeId) {

        //cafeId 존재 여부 검증 vo - 추후

        List<Review> reviews = reviewRepository.findAllByCafeId(cafeId);

        // - 추후
        // reviews 에 담긴 userId에 맞는
        // userName 과 userprofileImage를 가져와 dto에 넣기

        List<ReviewResponse> reviewsDto = reviews.stream().map(r-> new ReviewResponse(
                r.getStarRating(),
                null, //user name 부분
                r.getCreatedAt(),
                r.getContent(),
                null //user image 부분

        )).toList();

        return reviewsDto;
    }

    @Override
    public UpdateReviewResponse updateReview(long reviewId, String content) {

        Review review = reviewRepository.findById(reviewId);

        // cafeId가 존재하지 않을 경우 예외처리 - Optional사용

        review.updateContent(content);

        UpdateReviewResponse updateReviewResponse = new UpdateReviewResponse(review.getId());

        return updateReviewResponse;
    }


}
