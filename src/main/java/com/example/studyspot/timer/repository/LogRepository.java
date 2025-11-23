package com.example.studyspot.timer.repository;

import com.example.studyspot.timer.domain.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByTimerIdAndStartAtBetween(
            Long timerId,
            Timestamp startAt,
            Timestamp endAt
    );
}
