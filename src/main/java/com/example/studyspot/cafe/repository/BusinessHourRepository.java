package com.example.studyspot.cafe.repository;

import com.example.studyspot.cafe.domain.enums.DayOfWeek;
import com.example.studyspot.cafe.domain.model.BusinessHour;
import com.example.studyspot.cafe.domain.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessHourRepository extends JpaRepository<BusinessHour,Long> {
    List<BusinessHour> findByCafe(Cafe cafe);

    Optional<BusinessHour> findByCafeAndDayOfWeek(Cafe cafe, DayOfWeek dayOfWeek);
}
