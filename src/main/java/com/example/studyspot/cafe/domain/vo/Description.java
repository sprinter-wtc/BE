package com.example.studyspot.cafe.domain.vo;

import jakarta.persistence.Embeddable;

@Embeddable
public record Description(String value) {

    public Description {
        validateNotBlank(value);
    }

    private static void validateNotBlank(String value) {
        if (value != null && value.isBlank())
            throw new IllegalArgumentException("카페 이름은 비어있을 수 없습니다");
    }
}
