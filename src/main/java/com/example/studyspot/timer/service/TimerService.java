package com.example.studyspot.timer.service;

import com.example.studyspot.timer.dto.CreateTimerRequest;
import com.example.studyspot.timer.dto.CreateTimerResponse;

public interface TimerService {
    //타이머 등록
    CreateTimerResponse createTimer(CreateTimerRequest createTimerRequest);

}
