package com.example.studyspot.cafe.exception;

import com.example.studyspot.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum CafeErrorType implements ErrorType {
    LIMIT_TIME_MUST_BE_POSITIVE("CAFE-0001", HttpStatus.BAD_REQUEST, "제한 시간은 0이하일 수 없습니다"),
    INVALID_PHONE_NUMBER_PATTERN("CAFE-0002", HttpStatus.BAD_REQUEST, "매장 번호가 올바른 형태가 아닙니다"),
    PRICE_MUST_BE_POSITIVE("CAFE-0003", HttpStatus.BAD_REQUEST, "메뉴 가격은 음수일 수 없습니다"),

    CAFE_NOT_FOUND("CAFE-0004", HttpStatus.BAD_REQUEST, "카페를 찾을 수 없습니다"),
    TAG_NOT_FOUND("CAFE-0005", HttpStatus.INTERNAL_SERVER_ERROR, "카페의 태그가 존재하지 않습니다"),
    BUSINESS_HOUR_NOT_FOUND("CAFE-0006", HttpStatus.INTERNAL_SERVER_ERROR, "카페의 운영시간이 존재하지 않습니다"),
    REPRESENTATIVE_IMAGE_NOT_FOUND("CAFE-0007", HttpStatus.INTERNAL_SERVER_ERROR, "카페의 대표 사진이 존재하지 않습니다" );

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    CafeErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
