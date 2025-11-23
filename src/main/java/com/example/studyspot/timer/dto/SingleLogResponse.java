package com.example.studyspot.timer.dto;

public record SingleLogResponse (
        long startTime,
        long endTime,
        long duration
){
}
