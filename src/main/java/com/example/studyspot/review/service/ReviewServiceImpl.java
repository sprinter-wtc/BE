package com.example.studyspot.review.service;

import com.example.studyspot.review.domain.model.Review;
import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.dto.ReviewResponse;
import com.example.studyspot.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;


    @Override
    public ReviewResponse create(CreateReviewCommand command) {
        //카페 존재 여부 검증 - 추후

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
        return new ReviewResponse(stored.getId());

    }

    @Override
    public List<Review> findAllByCafeId(Long cafeId) {
        return List.of();
    }


}
