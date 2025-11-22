package com.example.studyspot.timer.service;

import com.example.studyspot.timer.dto.*;

import java.util.List;

public interface TimerService {
    //타이머 등록
    CreateTimerResponse createTimer(CreateTimerRequest createTimerRequest);
    
    //모든 타이머 조회
    List<ReadTimerResponse> getAllTimers();
    
    //로그 저장
    CreateLogResponse createLog(CreateLogCommand createLogCommand);
    
}
