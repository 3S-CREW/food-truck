package com.toss.foodtruck.domain.menu.dto;

import com.toss.foodtruck.domain.menu.entity.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuResponseDto {
    private Long menuId;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private Boolean isSoldOut;

    public static MenuResponseDto from(Menu menu) {
        return MenuResponseDto.builder()
                              .menuId(menu.getId())
                              .name(menu.getName())
                              .price(menu.getPrice())
                              .description(menu.getDescription())
                              .imageUrl(menu.getImageUrl())
                              .isSoldOut(menu.getIsSoldOut())
                              .build();
    }
}