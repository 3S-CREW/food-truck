package com.toss.foodtruck.global.response;

import lombok.Getter;

@Getter
public class ResultResponse<T> {

    private final int status;
    private final String code;
    private final String message;
    private final T data;

    public ResultResponse(ResultCode resultCode, T data) {
        this.status = resultCode.getStatus();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    // 데이터가 있는 경우
    public static <T> ResultResponse<T> of(ResultCode resultCode, T data) {
        return new ResultResponse<>(resultCode, data);
    }

    // 데이터가 없는 경우 (null 반환)
    public static <T> ResultResponse<T> of(ResultCode resultCode) {
        return new ResultResponse<>(resultCode, null);
    }
}