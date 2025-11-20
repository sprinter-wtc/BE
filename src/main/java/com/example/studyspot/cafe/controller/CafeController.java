package com.example.studyspot.cafe.controller;

import com.example.studyspot.cafe.dto.CafeDetailsDTO;
import com.example.studyspot.cafe.dto.CafeSimpleInfoDTO;
import com.example.studyspot.cafe.dto.response.CafeDetailsResponse;
import com.example.studyspot.cafe.dto.response.RecommendationCafeResponse;
import com.example.studyspot.cafe.service.CafeService;
import com.example.studyspot.common.api.ResponseEntityGenerator;
import com.example.studyspot.common.api.SuccessBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/cafes")
@RestController
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    @GetMapping("/details/{cafeId}")
    public ResponseEntity<SuccessBody<CafeDetailsResponse>> getCafeDetails (
            @PathVariable Long cafeId
    ) {
        CafeDetailsDTO details = cafeService.getCafeDetails(cafeId);
        return ResponseEntityGenerator.success(CafeDetailsResponse.from(details), HttpStatus.OK);
    }

    @GetMapping("/recommended")
    public ResponseEntity<SuccessBody<RecommendationCafeResponse>> getRecommendationCafes (
    ) {
        List<CafeSimpleInfoDTO> recommendationCafes = cafeService.getRecommendationCafes();
        return ResponseEntityGenerator.success(RecommendationCafeResponse.from(recommendationCafes), HttpStatus.OK);
    }
}
