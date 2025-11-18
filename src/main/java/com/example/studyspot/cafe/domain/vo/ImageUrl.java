package com.example.studyspot.cafe.domain.vo;

public record ImageUrl (String value) {

    public ImageUrl {
        validateNotBlank(value);
    }

    private static void validateNotBlank(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("이미지 url은 비어있을 수 없습니다");
    }
}
