package com.example.studyspot.cafe.domain.enums.tags;

import com.example.studyspot.cafe.domain.enums.BaseEnum;

public enum ParkingAvailability implements BaseEnum {
    AVAILABLE("주차 가능"),
    PAID("유료 주차"),
    NOT_AVAILABLE("주차 불가");

    private final String value;

    ParkingAvailability (String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
