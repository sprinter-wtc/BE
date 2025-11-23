package com.example.studyspot.cafe.domain.enums.tags;

import com.example.studyspot.cafe.domain.enums.BaseEnum;

public enum LightningLevel implements BaseEnum {
    DARK("어두움"),
    MEDIUM("중간"),
    BRIGHT("밝음");

    private final String value;

    LightningLevel(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
