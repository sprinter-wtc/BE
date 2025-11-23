package com.example.studyspot.timer.repository;

import com.example.studyspot.timer.domain.model.Timer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimerRepository extends JpaRepository<Timer, Long> {


}
