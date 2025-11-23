package com.example.studyspot.review.exception;

import com.example.studyspot.common.exception.ErrorType;
import org.springframework.http.HttpStatus;

public enum ReviewErrorType implements ErrorType {

    REVIEW_NOT_FOUND("REVIEW-0001",HttpStatus.NOT_FOUND, "해당 리뷰가 없습니다."),
    INVALID_REVIEW_STAR_RATING("REVIEW-0002",HttpStatus.BAD_REQUEST, "별점은 0.0 이상 5.0 이하만 가능 합니다."),
    INVALID_REVIEW_CONTENT_LENGTH("REVIEW-0003",HttpStatus.BAD_REQUEST, "리뷰 내용은 1자 이상 400자 이하만 가능 합니다."),
    INVALID_REVIEW_CONTENT("REVIEW-0003",HttpStatus.BAD_REQUEST, "유효하지 않은 리뷰 content가 들어왔습니다.");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;

    ReviewErrorType(String errorCode, HttpStatus httpStatus, String message) {
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
