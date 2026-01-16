package com.toss.foodtruck.domain.store.dto;

import com.toss.foodtruck.global.enums.StoreCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class StoreRegisterRequestDto {

    @Schema(description = "가게 이름", example = "홍길동의 푸드트럭")
    private String name;

    @Schema(description = "가게 카테고리", example = "KOREAN")
    private StoreCategory category;

    @Schema(description = "가게 소개글", example = "맛있는 한식 푸드트럭입니다.")
    private String introduction;

    @Schema(description = "주소", example = "서울시 강남구 역삼동")
    private String address;

    @Schema(description = "오픈 시간", example = "10:00:00", type = "string")
    private LocalTime openTime;

    @Schema(description = "마감 시간", example = "22:00:00", type = "string")
    private LocalTime closeTime;

    // 이미지는 추후 S3 연동 시 추가하거나, 별도 API로 처리
}