package com.example.studyspot.cafe.dto;

import com.example.studyspot.cafe.domain.model.BusinessHour;

import java.time.LocalTime;

public record BusinessHourDTO (
        String dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
){
    public static BusinessHourDTO from (BusinessHour businessHour) {
        return new BusinessHourDTO(
                businessHour.getDayOfWeek().getValue(),
                businessHour.getStartAt(),
                businessHour.getEnd_at()
        );
    }
}
