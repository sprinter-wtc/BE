package com.example.studyspot.cafe.repository;

import com.example.studyspot.cafe.domain.model.BusinessHour;
import com.example.studyspot.cafe.domain.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessHourRepository extends JpaRepository<BusinessHour,Long> {
    List<BusinessHour> findByCafe(Cafe cafe);
}
