package com.example.studyspot.timer.dto;
import java.util.List;

public record DailyLogsOfTimerResponse (
        Long id,
        String name,
        long totalTime,
        List<SingleLogResponse> logs
){
}
