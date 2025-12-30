package com.toss.foodtruck.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("ROLE_USER", "일반 사용자"),
    OWNER("ROLE_OWNER", "사장님"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}