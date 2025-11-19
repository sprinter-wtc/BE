package com.example.studyspot.cafe.repository;

import com.example.studyspot.cafe.domain.model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
}
