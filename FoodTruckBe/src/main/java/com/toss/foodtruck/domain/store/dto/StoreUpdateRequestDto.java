package com.toss.foodtruck.domain.store.dto;

import com.toss.foodtruck.global.enums.StoreCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class StoreUpdateRequestDto {
    @Schema(description = "가게 이름", example = "홍길동의 푸드트럭 리뉴얼")
    private String name;

    @Schema(description = "가게 카테고리", example = "SNACK")
    private StoreCategory category;

    @Schema(description = "가게 소개글", example = "더 맛있어졌습니다.")
    private String introduction;

    @Schema(description = "주소", example = "서울시 강남구 테헤란로")
    private String address;

    @Schema(description = "오픈 시간", example = "11:00:00", type = "string")
    private LocalTime openTime;

    @Schema(description = "마감 시간", example = "23:00:00", type = "string")
    private LocalTime closeTime;
}