package com.example.studyspot.timer.dto;

import com.example.studyspot.timer.domain.model.Timer;

public record ReadTimerResponse (
    Long id,
    String name,
    String color,
    Long duration
){
    public static ReadTimerResponse from(Timer timer){
        return new ReadTimerResponse(
                timer.getId(),
                timer.getName(),
                timer.getColor(),
                timer.getDuration()
        );
    }
}
