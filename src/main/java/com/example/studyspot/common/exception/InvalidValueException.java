package com.example.studyspot.common.exception;

public class InvalidValueException extends IllegalArgumentException {
    private final ErrorType errorType;

    public InvalidValueException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }
}
