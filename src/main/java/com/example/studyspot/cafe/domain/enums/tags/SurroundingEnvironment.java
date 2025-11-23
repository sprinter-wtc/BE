package com.example.studyspot.cafe.domain.enums.tags;

import com.example.studyspot.cafe.domain.enums.BaseEnum;

public enum SurroundingEnvironment implements BaseEnum {
    BUSY_STREET("번화가"),
    QUIET_STREET("조용한 골목"),
    NEAR_PARK("공원 근처"),
    NEAR_CAMPUS("캠퍼스 근처");
    private final String value;

    SurroundingEnvironment(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
