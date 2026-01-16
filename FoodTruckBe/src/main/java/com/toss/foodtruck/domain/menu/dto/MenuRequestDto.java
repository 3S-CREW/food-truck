package com.toss.foodtruck.domain.menu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuRequestDto {
    @Schema(description = "메뉴 이름", example = "옛날 떡볶이")
    private String name;

    @Schema(description = "가격", example = "4500")
    private Integer price;

    @Schema(description = "메뉴 설명", example = "매콤달콤한 학교 앞 떡볶이 맛")
    private String description;

    @Schema(description = "이미지 URL (선택)", example = "https://s3.../image.jpg")
    private String imageUrl;
}