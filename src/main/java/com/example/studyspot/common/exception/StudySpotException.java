package com.example.studyspot.common.exception;

public class StudySpotException extends RuntimeException{
    private final ErrorType errorType;

    public StudySpotException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
