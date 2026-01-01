package com.toss.foodtruck.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    // Auth (AU)
    LOGIN_SUCCESS(200, "AU001", "로그인에 성공하였습니다."),
    SIGNUP_SUCCESS(200, "AU002", "회원가입에 성공하였습니다."),
    NICKNAME_AVAILABLE(200, "AU003", "사용 가능한 닉네임입니다."),
    TOKEN_REISSUE_SUCCESS(200, "AU004", "토큰 재발급에 성공하였습니다."),
    LOGOUT_SUCCESS(200, "AU005", "로그아웃에 성공하였습니다."),

    // Member (M)
    GET_MY_INFO_SUCCESS(200, "M001", "내 정보 조회에 성공하였습니다."),
    UPDATE_PROFILE_SUCCESS(200, "M002", "프로필 정보 수정에 성공하였습니다."),

    // Store (S) - User
    GET_STORE_MAP_SUCCESS(200, "S001", "주변 가게 목록 조회에 성공하였습니다."),
    SEARCH_STORE_SUCCESS(200, "S002", "가게 검색에 성공하였습니다."),
    GET_STORE_DETAIL_SUCCESS(200, "S003", "가게 상세 조회에 성공하였습니다."),

    // Store (S) - Owner
    CREATE_STORE_SUCCESS(200, "S004", "가게 등록에 성공하였습니다."),
    UPDATE_STORE_STATUS_SUCCESS(200, "S005", "영업 상태 및 위치 업데이트에 성공하였습니다."),
    UPDATE_STORE_NOTICE_SUCCESS(200, "S006", "가게 공지 수정에 성공하였습니다."),
    UPDATE_STORE_INFO_SUCCESS(200, "S007", "가게 정보 수정에 성공하였습니다."),
    GET_MY_STORE_SUCCESS(200, "S007", "내 가게 조회에 성공하였습니다."),

    // Menu (MN)
    GET_MENU_LIST_SUCCESS(200, "MN001", "메뉴 목록 조회에 성공하였습니다."),
    CREATE_MENU_SUCCESS(200, "MN002", "메뉴 추가에 성공하였습니다."),
    UPDATE_MENU_SUCCESS(200, "MN003", "메뉴 수정에 성공하였습니다."),
    DELETE_MENU_SUCCESS(200, "MN004", "메뉴 삭제에 성공하였습니다."),
    TOGGLE_MENU_SOLDOUT_SUCCESS(200, "MN005", "메뉴 품절 상태 변경에 성공하였습니다."),

    // Review (R)
    GET_REVIEW_LIST_SUCCESS(200, "R001", "리뷰 목록 조회에 성공하였습니다."),
    CREATE_REVIEW_SUCCESS(200, "R002", "리뷰 작성에 성공하였습니다."),
    DELETE_REVIEW_SUCCESS(200, "R003", "리뷰 삭제에 성공하였습니다."),
    CREATE_REVIEW_REPLY_SUCCESS(200, "R004", "사장님 답글 등록에 성공하였습니다."),

    // Likes (L)
    TOGGLE_LIKE_SUCCESS(200, "L001", "찜 등록/취소 처리에 성공하였습니다."),
    GET_MY_LIKE_LIST_SUCCESS(200, "L002", "내 찜 목록 조회에 성공하였습니다."),

    // File (F)
    FILE_UPLOAD_SUCCESS(200, "F001", "파일 업로드에 성공하였습니다."),

    // Report (RP)
    CREATE_REPORT_SUCCESS(200, "RP001", "신고 접수에 성공하였습니다."),

    // Admin (AD)
    GET_ADMIN_DASHBOARD_SUCCESS(200, "AD001", "대시보드 통계 조회 성공"),
    GET_REPORT_LIST_SUCCESS(200, "AD002", "신고 목록 조회 성공"),
    PROCESS_REPORT_SUCCESS(200, "AD003", "신고 처리 완료"),
    GET_ALL_STORES_SUCCESS(200, "AD004", "전체 가게 목록 조회 성공"),
    DELETE_STORE_ADMIN_SUCCESS(200, "AD005", "가게 강제 삭제 성공"),
    DELETE_REVIEW_ADMIN_SUCCESS(200, "AD006", "리뷰 강제 삭제 성공");

    private final int status;
    private final String code;
    private final String message;
}