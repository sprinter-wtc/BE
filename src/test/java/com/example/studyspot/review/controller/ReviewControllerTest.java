package com.example.studyspot.review.controller;

import com.example.studyspot.review.dto.CreateReviewCommand;
import com.example.studyspot.review.dto.CreateReviewRequest;
import com.example.studyspot.review.dto.CreateReviewResponse;
import com.example.studyspot.review.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @MockitoBean
    private ReviewService reviewService;

    @Test
    void 리뷰_생성_검증() throws Exception{
        //Service 부분 Mock 처리
        CreateReviewResponse mockResponse = new CreateReviewResponse(0L);
        when(reviewService.create(any(CreateReviewCommand.class))).thenReturn(mockResponse);

        // 실제 request값 정해주기
        CreateReviewRequest body = new CreateReviewRequest(5.0, "커피 좋아요");

        String json = objectMapper.writeValueAsString(body);

        mockMvc.perform(
                post("/reviews/{cafeId}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(0L));

    }
}