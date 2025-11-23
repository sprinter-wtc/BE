package com.example.studyspot.cafe.domain.enums;

import lombok.Getter;

@Getter
public enum Purpose implements BaseEnum {
    BOOK("책"),
    LAPTOP("노트북"),
    DATE("데이트"),
    REST("휴식"),
    PHOTO_SPOT("포토스팟"),
    GROUP("단체");

    private final String value;

    Purpose(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
