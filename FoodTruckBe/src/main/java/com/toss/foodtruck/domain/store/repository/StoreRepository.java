package com.toss.foodtruck.domain.store.repository;

import com.toss.foodtruck.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    // 사장님 (Member) id 로 내 가게 찾기
    Optional<Store> findByMemberId(Long memberId);

    // 가게 이름 중복 확인용
    boolean existsByName(String name);
}
