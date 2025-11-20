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
public class LimitTime {
    private static final long MIN = 1;

    private long value;

    public LimitTime (long value) {
        validateMin(value);
        this.value = value;
    }

    private static void validateMin(long value) {
        if (value < MIN)
            throw new StudySpotException(CafeErrorType.LIMIT_TIME_MUST_BE_POSITIVE);
    }
}
