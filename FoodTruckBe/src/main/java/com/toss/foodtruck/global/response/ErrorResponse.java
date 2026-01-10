package com.toss.foodtruck.global.response;

import com.toss.foodtruck.global.exception.ErrorCode;
import jakarta.validation.ConstraintViolation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;
    private String code;
    private String message;
    private List<CustomFieldError> errors;

    private ErrorResponse(final ErrorCode code, final List<CustomFieldError> errors) {
        this.status = code.getStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.errors = errors;
    }

    private ErrorResponse(final ErrorCode code) {
        this.status = code.getStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.errors = new ArrayList<>();
    }

    // BindingResult (valid 에러)
    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new ErrorResponse(code, CustomFieldError.of(bindingResult));
    }

    // ConstraintViolation (validated 에러)
    public static ErrorResponse of(final ErrorCode code, final Set<ConstraintViolation<?>> constraintViolations) {
        return new ErrorResponse(code, CustomFieldError.of(constraintViolations));
    }

    // 필수 파라미터 누락
    public static ErrorResponse of(final ErrorCode code, final String missingParameterName) {
        return new ErrorResponse(code, CustomFieldError.of(missingParameterName, "", "파라미터가 필요합니다."));
    }

    // 일반 에러
    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final ErrorCode code, final List<CustomFieldError> errors) {
        return new ErrorResponse(code, errors);
    }

    // 타입 불일치 에러
    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<CustomFieldError> errors = CustomFieldError.of(e.getName(), value, e.getErrorCode());
        return new ErrorResponse(ErrorCode.INVALID_TYPE_VALUE, errors);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CustomFieldError {

        private String field;
        private String value;
        private String reason;

        public CustomFieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<CustomFieldError> of(final String field, final String value, final String reason) {
            final List<CustomFieldError> customFieldErrors = new ArrayList<>();
            customFieldErrors.add(new CustomFieldError(field, value, reason));
            return customFieldErrors;
        }

        public static List<CustomFieldError> of(final BindingResult bindingResult) {
            final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                              .map(error -> new CustomFieldError(
                                  error.getField(),
                                  error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                                  error.getDefaultMessage()))
                              .collect(Collectors.toList());
        }

        public static List<CustomFieldError> of(final Set<ConstraintViolation<?>> constraintViolations) {
            final List<ConstraintViolation<?>> lists = new ArrayList<>(constraintViolations);
            return lists.stream()
                        .map(error -> {
                            final String invalidValue = error.getInvalidValue() == null ? "" : error.getInvalidValue().toString();
                            // PropertyPath 파싱 (예: method.param -> param)
                            final int index = error.getPropertyPath().toString().indexOf(".");
                            final String propertyPath = index > 0
                                ? error.getPropertyPath().toString().substring(index + 1)
                                : error.getPropertyPath().toString();
                            return new CustomFieldError(propertyPath, invalidValue, error.getMessage());
                        })
                        .collect(Collectors.toList());
        }
    }
}