package com.toss.foodtruck.domain.store.entity;

import com.toss.foodtruck.domain.member.entity.Member;
import com.toss.foodtruck.global.common.BaseTimeEntity;
import com.toss.foodtruck.global.enums.StoreCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "store")
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    // [핵심] 사장님(Member) 1명당 가게 1개 (1:1 관계)
    // FetchType.LAZY : 가게 정보 조회할 때 사장님 정보는 나중에 필요할 때 가져옴 (성능 최적화)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "name", nullable = false, length = 50)
    private String name; // 가게 이름

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction; // 가게 소개

    @Column(name = "image_url")
    private String imageUrl; // 가게 대표 이미지 (S3 URL)

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private StoreCategory category; // 카테고리 (한식, 분식 등)

    @Column(name = "address")
    private String address; // 주소

    @Column(name = "is_open", nullable = false)
    @Builder.Default
    private Boolean isOpen = false; // 영업 여부 (기본값 false)

    @Column(name = "open_time")
    private LocalTime openTime; // 오픈 시간

    @Column(name = "close_time")
    private LocalTime closeTime; // 마감 시간

    // --- [비즈니스 편의 메서드] ---

    // 가게 정보 수정
    public void updateStoreInfo(String name, String introduction, String address, StoreCategory category, LocalTime openTime, LocalTime closeTime) {
        this.name = name;
        this.introduction = introduction;
        this.address = address;
        this.category = category;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    // 가게 이미지 수정
    public void updateImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // 영업 상태 변경 (오픈/마감)
    public void changeStatus(boolean isOpen) {
        this.isOpen = isOpen;
    }
}