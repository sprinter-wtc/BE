package com.example.studyspot.timer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record CreateTimerRequest(
        @NotBlank
        String name,
        @NotBlank
        @Pattern(regexp = "^#[0-9]+$")
        String color,
        Long duration
) {
}
