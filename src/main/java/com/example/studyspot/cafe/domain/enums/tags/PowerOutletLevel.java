package com.example.studyspot.cafe.domain.enums.tags;

public enum PowerOutletLevel {
    NONE("콘센트 없음"),
    SOME("콘센트 있음"),
    MANY("콘센트 많음");

    private final String value;

    PowerOutletLevel(String value) {
        this.value = value;
    }
}
