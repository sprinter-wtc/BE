package com.example.studyspot.cafe.domain.enums;

import lombok.Getter;

@Getter
public enum Category {
    CAFE("카페"),
    STUDY_CAFE("스터디카페"),
    STUDY_ROOM("독서실");

    private final String value;

    Category(String value) {
        this.value = value;
    }
}
