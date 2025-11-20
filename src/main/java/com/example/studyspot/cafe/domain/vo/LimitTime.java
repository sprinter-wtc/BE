package com.example.studyspot.cafe.domain.vo;

import com.example.studyspot.cafe.exception.CafeErrorType;
import com.example.studyspot.common.exception.StudySpotException;
import jakarta.persistence.Embeddable;

@Embeddable
public record LimitTime(long value) {
    private static final long MIN = 1;

    public LimitTime {
        validateMin(value);
    }

    private static void validateMin(long value) {
        if (value < MIN)
            throw new StudySpotException(CafeErrorType.LIMIT_TIME_MUST_BE_POSITIVE);
    }
}
