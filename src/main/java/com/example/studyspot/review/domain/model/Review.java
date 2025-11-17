package com.example.studyspot.review.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Review {
    @Id
    private Long id;

    private Long uuserId;
    private Long cafeId;
    private Double starRating;
    private LocalDateTime createdAt; //타입 timeStamp
    private String content;

    public void updateContent(String content) {
        //별점도 추후 추가?
        this.content = content;
    }
}
