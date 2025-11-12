package com.example.studyspot.review.controller;

import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.dto.CreateReviewRequest;
import com.example.studyspot.review.dto.ReviewResponse;
import com.example.studyspot.review.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    //Create
    @PostMapping("/{cafeId}")
    public ResponseEntity<ReviewResponse> create(
            @PathVariable Long cafeId,
            @Valid @RequestBody CreateReviewRequest body
    ){
        ReviewResponse created = reviewService.create(
                new CreateReviewCommand(cafeId, body.starRating(), body.content()));

        return ResponseEntity.status(201).body(created);
    }




}
