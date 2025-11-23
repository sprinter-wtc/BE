package com.example.studyspot.timer.dto;

import java.util.List;

public record DailyStudySummaryResponse(
        Long timerId,
        String name,
        long totalDuration,
        List<DailyStudyOfDayResponse> durationOfDate
){
}
