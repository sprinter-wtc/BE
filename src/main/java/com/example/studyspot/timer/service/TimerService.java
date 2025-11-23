package com.example.studyspot.timer.service;

import com.example.studyspot.common.annotation.UserId;
import com.example.studyspot.timer.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface TimerService {
    //타이머 등록
    CreateTimerResponse createTimer(CreateTimerRequest createTimerRequest, Long userId);
    
    //모든 타이머 조회
    List<ReadTimerResponse> getAllTimers(Long userId);

    //원하는 날짜 사이의 데일리 공부시간 조회
    ReadLogsOfTimer getDailyStudySummary(List<Long> timerId, long start, long end, Long userId,boolean withTotalTime);

    //로그 저장
    CreateLogResponse createLog(CreateLogCommand createLogCommand, Long userId);

    // 원하는 날짜의 공부 log 조회
    ReadDailyLogsResponse getDailyLogsOfDay(List<Long> timerIds, long day, Long userId);
}
