package com.example.studyspot.review.service;

import com.example.studyspot.common.exception.StudySpotException;
import com.example.studyspot.review.domain.model.Review;
import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.dto.CreateReviewResponse;
import com.example.studyspot.review.dto.UpdateReviewResponse;
import com.example.studyspot.review.exception.ReviewErrorType;
import com.example.studyspot.review.repository.MemoryReviewRepository;
import com.example.studyspot.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ReviewServiceImplTest {

    ReviewRepository reviewRepository = new MemoryReviewRepository();
    ReviewService reviewService = new ReviewServiceImpl(reviewRepository);

    @Test
    @DisplayName("dto를 사용하여 리뷰 생성 검증")
    void 리뷰_생성_검증() {
        //given
        CreateReviewCommand createReviewCommand = new CreateReviewCommand(1L, 4.5, "강아지랑 오기 좋아요");
        //when
        CreateReviewResponse reviewResponse = reviewService.createReview(createReviewCommand);
        //then
        assertThat(reviewResponse.id()).isNotNull();
    }

    //추후 작성
    @Test
    @DisplayName("리뷰 생성시, 서비스 계층에서 리뷰를 남기려는 카페의 아이디가 유효한 아이디인지 확인해야한다.")
    void 서비스계층의_유효성_검사_확인() {

    }

    @Test
    void 카페아이디_존재_여부_검사_예외_확인() {

    }

    @Test
    void DTO에_리뷰작성자_ID에_매핑되는_사용자_이름이_들어갔는지_확인() {

    }

    @Test
    void 리뷰_수정이후_동일한_리뷰_아이디를_갖는지_확인() {
        //given
        String updateContent = "고양이랑 오기 좋아요";
        Double updateStarRating = 5.0;
        CreateReviewCommand createReviewCommand = new CreateReviewCommand(1L, 4.5, "강아지랑 오기 좋아요");
        CreateReviewResponse createReviewResponse = reviewService.createReview(createReviewCommand);

        //when
        UpdateReviewResponse updateReviewResponse = reviewService.updateReview(createReviewResponse.id(), updateStarRating, updateContent);

        //then
        assertThat(updateReviewResponse.id()).isEqualTo(createReviewResponse.id());

    }

    @Test
    void 존재하지_않는_리뷰_아이디에_수정이_들어올_경우_예외_확인() {
        //given
        String updateContent = "고양이랑 오기 좋아요";
        Double updateStarRating = 5.0;

        //when & then
        assertThatThrownBy(() -> reviewService.updateReview(-1L,updateStarRating, updateContent))
                .isInstanceOf(StudySpotException.class)
                .hasMessage(ReviewErrorType.REVIEW_NOT_FOUND.getMessage());

    }

    @Test
    void 리뷰_삭제시_존재하지_않는_리뷰일_경우_예외_확인() {
        assertThatThrownBy(()-> reviewService.deleteReview(-1L))
                .isInstanceOf(StudySpotException.class)
                .hasMessage(ReviewErrorType.REVIEW_NOT_FOUND.getMessage());
    }

}