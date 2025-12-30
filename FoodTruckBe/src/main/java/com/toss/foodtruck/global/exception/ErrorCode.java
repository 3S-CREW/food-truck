package com.toss.foodtruck.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Global (G)
    INTERNAL_SERVER_ERROR(500, "G001", "내부 서버 오류입니다."),
    METHOD_NOT_ALLOWED(405, "G002", "허용되지 않은 HTTP method입니다."),
    INVALID_INPUT_VALUE(400, "G003", "유효하지 않은 입력입니다."),
    INVALID_TYPE_VALUE(400, "G004", "입력 타입이 유효하지 않습니다."),
    HTTP_MESSAGE_NOT_READABLE(400, "G005", "request message body가 없거나, 값 타입이 올바르지 않습니다."),
    HTTP_HEADER_INVALID(400, "G006", "request header가 유효하지 않습니다."),
    RESOURCE_NOT_FOUND(404, "G007", "요청한 리소스를 찾을 수 없습니다."),

    // Auth (AU)
    INVALID_TOKEN(401, "AU001", "유효하지 않은 토큰입니다."),
    EXPIRED_ACCESS_TOKEN(401, "AU002", "Access Token이 만료되었습니다. 토큰을 재발급해주세요."),
    ACCESS_DENIED(403, "AU003", "접근 권한이 없습니다."),
    UNAUTHORIZED_ACCESS(401, "AU004", "인증 정보가 없거나 유효하지 않습니다."),

    // Member (M)
    MEMBER_NOT_FOUND(404, "M001", "존재하지 않는 회원입니다."),
    NICKNAME_DUPLICATED(409, "M002", "이미 존재하는 닉네임입니다."),

    // Store (S)
    STORE_NOT_FOUND(404, "S001", "존재하지 않는 가게입니다."),
    ALREADY_HAS_STORE(409, "S002", "이미 등록된 가게가 있습니다."),
    NOT_STORE_OWNER(403, "S003", "해당 가게의 사장님이 아닙니다."),

    // Menu (MN)
    MENU_NOT_FOUND(404, "MN001", "존재하지 않는 메뉴입니다."),

    // Review (R)
    REVIEW_NOT_FOUND(404, "R001", "존재하지 않는 리뷰입니다."),
    NOT_REVIEW_WRITER(403, "R002", "리뷰 작성자만 삭제할 수 있습니다."),

    // File Upload (F)
    FILE_UPLOAD_ERROR(500, "F001", "파일 업로드에 실패했습니다."),
    INVALID_FILE_TYPE(400, "F002", "지원하지 않는 파일 형식입니다."),
    FILE_SIZE_EXCEED(400, "F003", "파일 크기가 제한을 초과했습니다."),
    FILE_NOT_FOUND(404, "F004", "파일을 찾을 수 없습니다."),

    // Report (RP)
    REPORT_NOT_FOUND(404, "RP001", "존재하지 않는 신고 내역입니다."),

    // Admin (AD)
    ADMIN_ACCESS_DENIED(403, "AD001", "관리자 권한이 필요합니다.");

    private final int status;
    private final String code;
    private final String message;
}