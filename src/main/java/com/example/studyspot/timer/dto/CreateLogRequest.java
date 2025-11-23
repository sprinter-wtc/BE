package com.example.studyspot.timer.dto;

import java.time.LocalDateTime;

public record CreateLogRequest (
    long startAt,
    long endAt,
    Long focusDuration
){
}
