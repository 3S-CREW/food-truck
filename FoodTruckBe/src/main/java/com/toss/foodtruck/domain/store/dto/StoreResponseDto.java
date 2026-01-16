package com.toss.foodtruck.domain.store.dto;

import com.toss.foodtruck.domain.store.entity.Store;
import com.toss.foodtruck.global.enums.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponseDto {
    private Long storeId;
    private String name;
    private StoreCategory category;
    private String introduction;
    private String imageUrl;
    private String address;
    private Boolean isOpen;
    private LocalTime openTime;
    private LocalTime closeTime;

    // Entity -> DTO 변환 메서드 (편의성)
    public static StoreResponseDto from(Store store) {
        return StoreResponseDto.builder()
                   .storeId(store.getId())
                   .name(store.getName())
                   .category(store.getCategory())
                   .introduction(store.getIntroduction())
                   .imageUrl(store.getImageUrl())
                   .address(store.getAddress())
                   .isOpen(store.getIsOpen())
                   .openTime(store.getOpenTime())
                   .closeTime(store.getCloseTime())
                   .build();
    }
}