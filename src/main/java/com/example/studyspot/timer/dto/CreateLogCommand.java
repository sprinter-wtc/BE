package com.example.studyspot.timer.dto;

import java.time.LocalDateTime;

public record CreateLogCommand (
        Long timerId,
        long startAt,
        long endAt,
        Long focusDuration
){

}
