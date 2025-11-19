package com.example.studyspot.review.domain.model;

import com.example.studyspot.common.exception.StudySpotException;
import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.exception.ReviewErrorType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uuserId;
    private Long cafeId;
    private Double starRating;
    private LocalDateTime createdAt; //타입 timeStamp

    @Column(nullable = false,length = 400)
    private String content;

    private Review(Long id, Long uuserId, Long cafeId, Double starRating, LocalDateTime createdAt, String content) {
        this.id = id;
        this.uuserId = uuserId;
        this.cafeId = cafeId;
        Validator.validateStarRating(starRating);
        this.starRating = starRating;
        this.createdAt = createdAt;
        Validator.validateContent(content);
        this.content = content;
    }

    public static Review from(Long id, Long uuserId, CreateReviewCommand command){

        return new Review(
                id,
                uuserId,
                command.cafeId(),
                command.starRating(),
                LocalDateTime.now(),
                command.content()
        );

    }

    public void updateContent(String content) {
        Validator.validateContent(content);
        this.content = content;
    }

    public void updateStarRating(Double starRating){
        Validator.validateStarRating(starRating);
        this.starRating = starRating;
    }

    private static class Validator{
        private static void validateStarRating(double starRating){
            if (starRating < 0.0 || starRating > 5.0) {
                throw new StudySpotException(ReviewErrorType.INVALID_REVIEW_STAR_RATING);
            }
        }

        private static void validateContent(String content){
            if (content==null || content.isBlank()){
                throw new StudySpotException(ReviewErrorType.INVALID_REVIEW_CONTENT);
            }
            if (content.length() > 400){
                throw new StudySpotException(ReviewErrorType.INVALID_REVIEW_CONTENT_LENGTH);
            }
        }
    }
}
