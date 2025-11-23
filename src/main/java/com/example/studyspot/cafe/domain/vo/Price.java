package com.example.studyspot.cafe.domain.vo;

import com.example.studyspot.cafe.exception.CafeErrorType;
import com.example.studyspot.common.exception.StudySpotException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Price {
    private static final long ZERO = 0;

    private long value;

    public Price (long value) {
        validateIsPositive(value);
        this.value = value;
    }

    private static void validateIsPositive(long value) {
        if (value < ZERO )
            throw new StudySpotException(CafeErrorType.PRICE_MUST_BE_POSITIVE);
    }
}
