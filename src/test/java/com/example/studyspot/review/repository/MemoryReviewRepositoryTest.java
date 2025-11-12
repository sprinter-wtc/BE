package com.example.studyspot.review.repository;

import com.example.studyspot.review.domain.model.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryReviewRepositoryTest {

    ReviewRepository reviewRepository;

    @BeforeEach
    void setUp(){
        reviewRepository = new MemoryReviewRepository();
    }

    @AfterEach
    void clear(){
        reviewRepository.clear();
    }

    @Test
    @DisplayName("리뷰가 잘 저장되었는지 확인하는 테스트")
    void 리뷰_저장_테스트(){
        Review review = new Review(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                2L,
                4.5,
                LocalDateTime.now(),
                "강아지랑 오기 좋아요."
        );

        Review stored = reviewRepository.save(review);
        Review newReview = reviewRepository.findById(stored.getId());
        System.out.println(newReview);
        assertThat(review).isSameAs(newReview);
    }

    @Test
    @DisplayName("memory에서 저장할때, memory 내부에서 id 값이 중복되지 않도록 0에서 부터 증가하는 형태로 부여하는데, 잘 반영되는지 확인")
    void 리뷰_식별_아이디_확인(){
        Review review = new Review(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                2L,
                4.5,
                LocalDateTime.now(),
                "고양이랑 오기 좋아요."
        );
        Review review2 = new Review(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                3L,
                5.0,
                LocalDateTime.now(),
                "커피맛집"
        );

        Review stored = reviewRepository.save(review);
        Review stored2 = reviewRepository.save(review2);

        assertThat(stored.getId()).isNotEqualTo(stored2.getId());


    }


}