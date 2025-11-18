package com.example.studyspot.cafe.domain.enums.tags;

public enum StayDurationPolicy {
    LIMITED("있음"),
    UNLIMITED("없음");

    private final String value;

    StayDurationPolicy(String value) {
        this.value = value;
    }
}
