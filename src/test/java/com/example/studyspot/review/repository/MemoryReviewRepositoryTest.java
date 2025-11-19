package com.example.studyspot.review.repository;

import com.example.studyspot.review.domain.model.Review;
import com.example.studyspot.review.dto.CreateReviewCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryReviewRepositoryTest {

    ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        reviewRepository = new MemoryReviewRepository();
    }

    @AfterEach
    void clear() {
        reviewRepository.clear();
    }

    @Test
    @DisplayName("리뷰가 잘 저장되었는지 확인하는 테스트")
    void 리뷰_저장_테스트() {
        //given
        Review review = Review.from(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId

                new CreateReviewCommand(2L, 4.5, "강아지랑 오기 좋아요.")
        );

        //when
        Review saved = reviewRepository.save(review);
        Review found = reviewRepository.findById(saved.getId()).orElseThrow();

        //then
        assertThat(review).isSameAs(found);
    }

    @Test
    @DisplayName("memory에서 저장할때, memory 내부에서 id 값이 중복되지 않도록 0에서 부터 증가하는 형태로 부여하는데, 잘 반영되는지 확인")
    void 리뷰_식별_아이디_확인() {
        //given
        Review review = Review.from(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                new CreateReviewCommand(2L, 4.5, "고양이랑 오기 좋아요.")

        );
        Review review2 = Review.from(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                new CreateReviewCommand(3L, 5.0, "커피맛집")
        );

        //when
        Review stored = reviewRepository.save(review);
        Review stored2 = reviewRepository.save(review2);

        //then
        assertThat(stored.getId()).isNotEqualTo(stored2.getId());
    }

    @Test
    void 카페_아이디_기반_리뷰목록_찾기() {
        //given
        Review review1 = Review.from(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                new CreateReviewCommand(10L, 4.5, "고양이랑 오기 좋아요.")
        );
        Review review2 = Review.from(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                new CreateReviewCommand(10L, 4.0, "노트북하기 좋아요.")
        );
        Review review3 = Review.from(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                new CreateReviewCommand(40L, 5.0, "아인슈페너 맛집.")
        );
        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);

        //when
        List<Review> result = reviewRepository.findAllByCafeId(10L);

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).extracting(Review::getCafeId)
                .containsOnly(10L);
        assertThat(result).extracting(Review::getContent)
                .containsExactlyInAnyOrder(
                        "고양이랑 오기 좋아요."
                        , "노트북하기 좋아요.");

    }

    @Test
    void 리뷰_수정_검증() {
        //given
        String updateContent = "강아지랑 오기 좋아요";
        Double updateStarRating = 5.0;

        Review review1 = Review.from(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                new CreateReviewCommand(10L, 4.5, "고양이랑 오기 좋아요.")
        );
        Review saved = reviewRepository.save(review1);
        Review found = reviewRepository.findById(saved.getId()).orElseThrow();

        //when
        found.updateContent(updateContent);
        found.updateStarRating(updateStarRating);
        Review updated = reviewRepository.findById(saved.getId()).orElseThrow();

        //then
        assertThat(updated.getContent()).isEqualTo(updateContent);
        assertThat(updated.getStarRating()).isEqualTo(updateStarRating);

    }

    @Test
    void 리뷰_삭제_검증() {
        //given
        Review review1 = Review.from(
                null, // 추후 DB 사용시 변환하기
                null, // 추후 인증 로직 구현시 사용 userId
                new CreateReviewCommand(10L, 4.5, "고양이랑 오기 좋아요.")
        );
        Review saved = reviewRepository.save(review1);

        //when
        reviewRepository.delete(saved);
        Optional<Review> deleted = reviewRepository.findById(saved.getId());

        //then
        assertThat(deleted).isEmpty();
    }

}