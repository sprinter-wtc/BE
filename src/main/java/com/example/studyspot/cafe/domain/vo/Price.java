package com.example.studyspot.cafe.domain.vo;

import com.example.studyspot.cafe.exception.CafeErrorType;
import com.example.studyspot.common.exception.StudySpotException;
import jakarta.persistence.Embeddable;

@Embeddable
public record Price (long value) {
    private static final long ZERO = 0;


    public Price {
        validateIsPositive(value);
    }

    private static void validateIsPositive(long value) {
        if (value < ZERO )
            throw new StudySpotException(CafeErrorType.PRICE_MUST_BE_POSITIVE);
    }
}
