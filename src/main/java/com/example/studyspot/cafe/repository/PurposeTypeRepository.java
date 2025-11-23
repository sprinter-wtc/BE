package com.example.studyspot.cafe.repository;

import com.example.studyspot.cafe.domain.model.Cafe;
import com.example.studyspot.cafe.domain.model.PurposeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurposeTypeRepository extends JpaRepository<PurposeType, Integer> {
    List<PurposeType> findByCafe(Cafe cafe);
}
