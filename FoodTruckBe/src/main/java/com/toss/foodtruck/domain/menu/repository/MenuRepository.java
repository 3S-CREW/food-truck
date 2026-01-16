package com.toss.foodtruck.domain.menu.repository;

import com.toss.foodtruck.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 특정 가게의 모든 메뉴 조회
    List<Menu> findAllByStoreId(Long storeId);
}