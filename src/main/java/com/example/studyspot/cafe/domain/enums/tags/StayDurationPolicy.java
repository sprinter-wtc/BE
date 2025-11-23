package com.example.studyspot.cafe.domain.enums.tags;

import com.example.studyspot.cafe.domain.enums.BaseEnum;

public enum StayDurationPolicy implements BaseEnum {
    LIMITED("시간제한 있음"),
    UNLIMITED("시간제한 없음");

    private final String value;

    StayDurationPolicy(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
