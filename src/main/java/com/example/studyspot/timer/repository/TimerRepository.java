package com.example.studyspot.timer.repository;

import com.example.studyspot.timer.domain.model.Timer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimerRepository extends JpaRepository<Timer, Long> {
    List<Timer> findByUuserId(Long uuserId);

}
