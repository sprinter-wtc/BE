package com.example.studyspot.review.controller;

import com.example.studyspot.review.domain.model.Review;
import com.example.studyspot.review.dto.*;
import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.dto.CreateReviewRequest;
import com.example.studyspot.review.dto.CreateReviewResponse;
import com.example.studyspot.review.dto.ReviewResponse;
import com.example.studyspot.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //Create
    @PostMapping("/{cafeId}")
    public ResponseEntity<CreateReviewResponse> createReview(
            @PathVariable Long cafeId,
            @Valid @RequestBody CreateReviewRequest body
    ) {
        CreateReviewResponse created = reviewService.createReview(
                new CreateReviewCommand(cafeId, body.starRating(), body.content()));

        return ResponseEntity.status(201).body(created);
    }

    //Read
    @GetMapping("/{cafeId}")
    public ResponseEntity<List<ReviewResponse>> getByCafeId(
            @PathVariable Long cafeId
    ) {
        List<ReviewResponse> body = reviewService.findAllByCafeId(cafeId);
        return ResponseEntity.ok(body);
    }

    //Update
    @PatchMapping("/{reviewId}")
    public ResponseEntity<UpdateReviewResponse> updateReview(
            @PathVariable Long reviewId,
            @Valid @RequestBody UpdateReviewRequest body
    ) {
        UpdateReviewResponse updated = reviewService.updateReview(reviewId, body.starRating(), body.content());
        return ResponseEntity.ok(updated);
    }

    //Delete
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}
