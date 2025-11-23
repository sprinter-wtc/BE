package com.example.studyspot.timer.dto;

public record DailyStudyOfDayResponse (
    long date,
    char day,
    long dailyTotalDuration
){
}
