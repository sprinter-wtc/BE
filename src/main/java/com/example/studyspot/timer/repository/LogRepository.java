package com.example.studyspot.timer.repository;

import com.example.studyspot.timer.domain.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}
