package com.example.studyspot.cafe.domain.enums.tags;

import com.example.studyspot.cafe.domain.enums.BaseEnum;

public enum PetFriendly implements BaseEnum {
    ALLOWED("애견 동반 가능"),
    NOT_ALLOWED("애견 동반 불가능");

    private final String value;

    PetFriendly(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
