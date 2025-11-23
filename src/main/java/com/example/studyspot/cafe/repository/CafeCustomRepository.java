package com.example.studyspot.cafe.repository;

import com.example.studyspot.cafe.domain.model.Cafe;
import com.example.studyspot.cafe.dto.CafeFilter;

import java.util.List;

public interface CafeCustomRepository {
    List<Cafe> findRecommendationCafes();

    List<Cafe> searchByFilter(CafeFilter cafeFilter);
}
