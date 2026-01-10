package com.toss.foodtruck.global.exception;

import com.toss.foodtruck.global.response.ErrorResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private List<ErrorResponse.CustomFieldError> errors = new ArrayList<>();

    // 메시지를 직접 지정하여 예외 발생
    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    // ErrorCode의 기본 메시지를 사용하여 예외 발생 (가장 많이 사용)
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // 상세 에러 목록(errors)을 포함하여 예외 발생 (Validation 실패 등)
    public BusinessException(ErrorCode errorCode, List<ErrorResponse.CustomFieldError> errors) {
        super(errorCode.getMessage());
        this.errors = errors;
        this.errorCode = errorCode;
    }
}