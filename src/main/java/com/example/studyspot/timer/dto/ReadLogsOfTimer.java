package com.example.studyspot.timer.dto;

import java.util.List;

public record ReadLogsOfTimer(
        List<DailyStudySummaryResponse> logsOfTimer,
        TotalStudyOfDayResponse totalTime
){
}
