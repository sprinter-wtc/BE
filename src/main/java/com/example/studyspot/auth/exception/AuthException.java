package com.example.studyspot.auth.exception;

import com.example.studyspot.common.exception.ErrorType;
import com.example.studyspot.common.exception.StudySpotException;

public class AuthException extends StudySpotException {

    public AuthException(ErrorType errorType) {
        super(errorType);
    }
}
