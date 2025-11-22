package com.example.studyspot.timer.service;

import com.example.studyspot.timer.dto.CreateLogCommand;
import com.example.studyspot.timer.dto.CreateLogResponse;
import com.example.studyspot.timer.dto.CreateTimerRequest;
import com.example.studyspot.timer.dto.CreateTimerResponse;

public interface TimerService {
    //타이머 등록
    CreateTimerResponse createTimer(CreateTimerRequest createTimerRequest);

    //로그 저장
    CreateLogResponse createLog(CreateLogCommand createLogCommand);
}
