package com.example.studyspot.cafe.domain.enums.tags;

import com.example.studyspot.cafe.domain.enums.BaseEnum;

public enum StayDurationPolicy implements BaseEnum {
    LIMITED("있음"),
    UNLIMITED("없음");

    private final String value;

    StayDurationPolicy(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
