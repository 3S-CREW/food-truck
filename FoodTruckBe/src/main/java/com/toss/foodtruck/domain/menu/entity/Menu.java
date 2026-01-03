package com.toss.foodtruck.domain.menu.entity;

import com.toss.foodtruck.domain.store.entity.Store;
import com.toss.foodtruck.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "menu")
public class Menu extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    // 여러 메뉴가 하나의 가게에 소속됨 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "name", nullable = false)
    private String name; // 메뉴 이름

    @Column(name = "price", nullable = false)
    private Integer price; // 가격

    @Column(name = "description")
    private String description; // 메뉴 설명

    @Column(name = "image_url")
    private String imageUrl; // 메뉴 사진

    @Column(name = "is_sold_out", nullable = false)
    @Builder.Default
    private Boolean isSoldOut = false; // 품절 여부 (기본값: 판매중)

    // 메뉴 정보 수정
    public void updateMenu(String name, Integer price, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // 품절 처리 토글
    public void changeSoldOutStatus(boolean isSoldOut) {
        this.isSoldOut = isSoldOut;
    }
}