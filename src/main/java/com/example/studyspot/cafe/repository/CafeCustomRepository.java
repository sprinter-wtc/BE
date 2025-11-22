package com.example.studyspot.cafe.repository;

import com.example.studyspot.cafe.domain.model.Cafe;

import java.util.List;

public interface CafeCustomRepository {
    List<Cafe> findRecommendationCafes ();
}
