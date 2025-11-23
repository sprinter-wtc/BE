package com.example.studyspot.cafe.domain.enums.tags;

import com.example.studyspot.cafe.domain.enums.BaseEnum;

public enum TransportLevel implements BaseEnum {
    NEAR_SUBWAY("지하철 근처"),
    NEAR_BUS_STOP("버스정류장 근처"),
    EASY_ACCESS("접근성 좋음");

    private final String value;

    TransportLevel(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
