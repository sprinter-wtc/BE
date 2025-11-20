package com.example.studyspot.cafe.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address  {
    private String value;

    public Address (String value) {
        validateNotBlank(value);
    }

    private static void validateNotBlank(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("카페 주소는 비어있을 수 없습니다");
    }
}
