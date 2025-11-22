package com.example.studyspot.review.domain.model;

import com.example.studyspot.common.exception.StudySpotException;
import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.exception.ReviewErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    @Test
    @DisplayName("별점 입력이 6보다 클경우 예외 처리")
    void 별점_유효성_검사_예외_처리() {
        assertThatThrownBy(() -> Review.from(
                        0L,
                        1L,
                        new CreateReviewCommand(1L, 6.0, "안녕")
                )
        ).isInstanceOf(StudySpotException.class)
                .hasMessage(ReviewErrorType.INVALID_REVIEW_STAR_RATING.getMessage());
    }

    @Test
    @DisplayName("내용 입력 길이가 400보다 클경우 예외 처리")
    void 내용_유효성_검사_예외_처리() {
        String contentOver400Length = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
        assertThatThrownBy(() -> Review.from(
                0L,
                1L,
                new CreateReviewCommand(1L, 6.0, contentOver400Length)
                )
        ).isInstanceOf(StudySpotException.class)
                .hasMessage(ReviewErrorType.INVALID_REVIEW_CONTENT_LENGTH.getMessage());
    }

}