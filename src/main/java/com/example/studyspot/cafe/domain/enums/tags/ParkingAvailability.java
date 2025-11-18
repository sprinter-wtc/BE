package com.example.studyspot.cafe.domain.enums.tags;

public enum ParkingAvailability  {
    AVAILABLE("주차 가능"),
    PAID("유료 주차"),
    NOT_AVAILABLE("주차 불가");

    private final String value;

    ParkingAvailability (String value) {
        this.value = value;
    }
}
