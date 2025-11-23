package com.example.studyspot.timer.dto;

import java.util.List;

public record ReadDailyLogsResponse (
        List<DailyLogsOfTimerResponse> dailyLogs
){
}
