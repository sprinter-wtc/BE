package com.example.studyspot.cafe.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CafeName {
    private String value;

    public CafeName (String value) {
        validateNotBlank(value);
        this.value = value;
    }

    private static void validateNotBlank(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("카페 이름은 비어있을 수 없습니다");
    }
}
