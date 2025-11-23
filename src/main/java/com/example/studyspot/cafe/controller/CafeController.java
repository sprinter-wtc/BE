package com.example.studyspot.cafe.controller;

import com.example.studyspot.cafe.domain.enums.Category;
import com.example.studyspot.cafe.domain.enums.Purpose;
import com.example.studyspot.cafe.domain.enums.tags.*;
import com.example.studyspot.cafe.dto.CafeDetailsDTO;
import com.example.studyspot.cafe.dto.CafeFilter;
import com.example.studyspot.cafe.dto.CafeSimpleInfoDTO;
import com.example.studyspot.cafe.dto.response.CafeDetailsResponse;
import com.example.studyspot.cafe.dto.response.CafeSearchResponse;
import com.example.studyspot.cafe.dto.response.RecommendationCafeResponse;
import com.example.studyspot.cafe.service.CafeService;
import com.example.studyspot.common.api.ResponseEntityGenerator;
import com.example.studyspot.common.api.SuccessBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public ResponseEntity<SuccessBody<CafeSearchResponse>> searchCafes (
            @RequestParam(required = false) String nameOfCafe,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Purpose purpose,
            @RequestParam(name = "lightning_level", required = false) LightningLevel lightningLevel,
            @RequestParam(name = "noise_level", required = false) NoiseLevel noiseLevel,
            @RequestParam(name = "power_outlet_level", required = false) PowerOutletLevel powerOutletLevel,
            @RequestParam(name = "stay_duration_policy", required = false) StayDurationPolicy stayDurationPolicy,
            @RequestParam(name = "parking_availability", required = false) ParkingAvailability parkingAvailability,
            @RequestParam(name = "transport_level", required = false) TransportLevel transportLevel ,
            @RequestParam(name = "surrounding_environment", required = false) SurroundingEnvironment surroundingEnvironment,
            @RequestParam(name = "pet_friendly", required = false) PetFriendly petFriendly
            ) {
        CafeFilter cafeFilter = new CafeFilter(
                nameOfCafe, category, purpose, lightningLevel, noiseLevel,
                powerOutletLevel, stayDurationPolicy, parkingAvailability,
                transportLevel, surroundingEnvironment, petFriendly
        );
        List<CafeSimpleInfoDTO> searchResults = cafeService.searchCafesByFilter(cafeFilter);
        return ResponseEntityGenerator.success(CafeSearchResponse.from(searchResults), HttpStatus.OK);
    }
}