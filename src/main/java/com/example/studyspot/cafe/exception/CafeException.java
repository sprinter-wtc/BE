package com.example.studyspot.cafe.exception;

import com.example.studyspot.common.exception.ErrorType;
import com.example.studyspot.common.exception.StudySpotException;

public class CafeException extends StudySpotException {
    public CafeException(ErrorType errorType) {
        super(errorType);
    }
}
