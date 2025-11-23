package com.example.studyspot.cafe.domain.enums.tags;

public enum PetFriendly {
    ALLOWED("애견 동반 가능"),
    NOT_ALLOWED("애견 동반 불가능");

    private final String value;

    PetFriendly(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
