package com.example.studyspot.cafe.domain.vo;

import com.example.studyspot.cafe.exception.CafeErrorType;
import com.example.studyspot.common.exception.StudySpotException;

public record PhoneNumber(String value) {
    private static final String  VALID_PATTERN = "^(010-\\d{4}-\\d{4}|0\\d{1,2}-\\d{3,4}-\\d{4})$";

    public PhoneNumber {
        validateNotBlank(value);
        validatePattern(value);
    }

    private static void validateNotBlank(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("번호는 비어있을 수 없습니다");
    }

    private static void validatePattern(String value) {
        if (!value.matches(VALID_PATTERN))
            throw new StudySpotException(CafeErrorType.INVALID_PHONE_NUMBER_PATTERN);
    }
}
