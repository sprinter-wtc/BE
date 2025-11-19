package com.example.studyspot.cafe.domain.enums.tags;

public enum LightningLevel {
    DARK("어두움"),
    MEDIUM("중간"),
    BRIGHT("밝음");

    private final String value;

    LightningLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
