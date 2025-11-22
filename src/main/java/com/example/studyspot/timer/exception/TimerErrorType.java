package com.example.studyspot.timer.exception;

import com.example.studyspot.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum TimerErrorType implements ErrorType {
    TIMER_NOT_FOUND("REVIEW-0001",HttpStatus.NOT_FOUND, "해당 타이머가 없습니다.");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    TimerErrorType(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
