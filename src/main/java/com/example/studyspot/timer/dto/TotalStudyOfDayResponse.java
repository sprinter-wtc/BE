package com.example.studyspot.timer.dto;

import java.util.List;

public record TotalStudyOfDayResponse (
        long totalDuration,
        List<DailyStudyOfDayResponse> durationOfDate
){
}
