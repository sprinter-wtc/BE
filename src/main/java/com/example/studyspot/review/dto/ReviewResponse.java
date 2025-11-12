package com.example.studyspot.review.dto;

import java.time.LocalDateTime;

public record ReviewResponse (
        double starRating,
        String name, //depending on user
        LocalDateTime createdAt,
        String content,
        String imageUrl //depending on user

){

}
