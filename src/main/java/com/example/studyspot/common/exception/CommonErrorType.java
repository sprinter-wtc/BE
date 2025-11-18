package com.example.studyspot.common.exception;

import org.springframework.http.HttpStatus;

public enum CommonErrorType implements ErrorType{
    UN_EXPECTED_EXCEPTION("COMMON-0001", HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 예상치 못한 에러가 발생했습니다.");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    CommonErrorType(String errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
