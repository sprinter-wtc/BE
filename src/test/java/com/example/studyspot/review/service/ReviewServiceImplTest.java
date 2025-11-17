package com.example.studyspot.review.service;

import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.dto.CreateReviewResponse;
import com.example.studyspot.review.repository.MemoryReviewRepository;
import com.example.studyspot.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReviewServiceImplTest {

    ReviewRepository reviewRepository = new MemoryReviewRepository();
    ReviewService reviewService = new ReviewServiceImpl(reviewRepository);

    @Test
    @DisplayName("dto를 사용하여 리뷰 생성 검증")
    void 리뷰_생성_검증(){
        CreateReviewCommand createReviewCommand = new CreateReviewCommand(1L,4.5,"강아지랑 오기 좋아요");
        CreateReviewResponse reviewResponse = reviewService.create(createReviewCommand);
        System.out.println("요청 결과 :" + reviewResponse.id());
    }

    //추후 작성
    @Test
    @DisplayName("리뷰 생성시, 서비스 계층에서 리뷰를 남기려는 카페의 아이디가 유효한 아이디인지 확인해야한다.")
    void 서비스계층의_유효성_검사_확인(){

    }

    @Test
    void 카페아이디_존재_여부_검사_예외_확인(){

    }

    @Test
    void DTO에_리뷰작성자_ID에_매핑되는_사용자_이름이_들어갔는지_확인(){

    }

}