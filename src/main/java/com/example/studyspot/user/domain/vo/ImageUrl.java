package com.example.studyspot.user.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageUrl  {
    private String value;

    public ImageUrl (String value) {
        validateNotBlank(value);
        this.value = value;
    }

    private static void validateNotBlank(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("유저 이미지 url은 비어있을 수 없습니다");
    }
}
