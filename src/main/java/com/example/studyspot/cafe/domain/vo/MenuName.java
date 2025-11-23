package com.example.studyspot.cafe.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuName {
    private String value;

    public MenuName (String value) {
        validateNotBlank(value);
        this.value = value;
    }

    private static void validateNotBlank(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("메뉴 이름은 비어있을 수 없습니다");
    }
}