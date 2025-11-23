package com.example.studyspot.timer.dto;

import java.util.List;

public record ReadTimersResponse (
        List<ReadTimerResponse> timers
){
}
