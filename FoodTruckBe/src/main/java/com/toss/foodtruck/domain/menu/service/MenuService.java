package com.toss.foodtruck.domain.menu.service;

import com.toss.foodtruck.domain.menu.dto.MenuRequestDto;
import com.toss.foodtruck.domain.menu.dto.MenuResponseDto;
import com.toss.foodtruck.domain.menu.entity.Menu;
import com.toss.foodtruck.domain.menu.repository.MenuRepository;
import com.toss.foodtruck.domain.store.entity.Store;
import com.toss.foodtruck.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    // 1. 메뉴 등록
    @Transactional
    public MenuResponseDto createMenu(Long memberId, MenuRequestDto request) {
        // 내 가게 찾기
        Store store = storeRepository.findByMemberId(memberId)
                                     .orElseThrow(() -> new RuntimeException("내 가게가 존재하지 않습니다."));

        // 메뉴 생성
        Menu menu = Menu.builder()
                        .store(store) // 연관관계 설정
                        .name(request.getName())
                        .price(request.getPrice())
                        .description(request.getDescription())
                        .imageUrl(request.getImageUrl())
                        .isSoldOut(false)
                        .build();

        menuRepository.save(menu);
        return MenuResponseDto.from(menu);
    }

    // 2. 내 가게 메뉴 목록 조회
    public List<MenuResponseDto> getMyMenus(Long memberId) {
        Store store = storeRepository.findByMemberId(memberId)
                                     .orElseThrow(() -> new RuntimeException("내 가게가 존재하지 않습니다."));

        return menuRepository.findAllByStoreId(store.getId()).stream()
                             .map(MenuResponseDto::from)
                             .collect(Collectors.toList());
    }

    // 3. 메뉴 수정
    @Transactional
    public MenuResponseDto updateMenu(Long memberId, Long menuId, MenuRequestDto request) {
        Menu menu = validateMenuOwnership(memberId, menuId);

        menu.updateMenu(request.getName(), request.getPrice(), request.getDescription(), request.getImageUrl());
        return MenuResponseDto.from(menu);
    }

    // 4. 메뉴 삭제
    @Transactional
    public void deleteMenu(Long memberId, Long menuId) {
        Menu menu = validateMenuOwnership(memberId, menuId);
        menuRepository.delete(menu);
    }

    // 5. 품절 상태 변경 (토글)
    @Transactional
    public boolean toggleSoldOut(Long memberId, Long menuId) {
        Menu menu = validateMenuOwnership(memberId, menuId);

        boolean newState = !menu.getIsSoldOut();
        menu.changeSoldOutStatus(newState);

        return newState;
    }

    // [검증 메서드] 요청한 메뉴가 내 가게의 메뉴인지 확인
    private Menu validateMenuOwnership(Long memberId, Long menuId) {
        Store store = storeRepository.findByMemberId(memberId)
                                     .orElseThrow(() -> new RuntimeException("내 가게가 없습니다."));

        Menu menu = menuRepository.findById(menuId)
                                  .orElseThrow(() -> new RuntimeException("존재하지 않는 메뉴입니다."));

        if (!menu.getStore().getId().equals(store.getId())) {
            throw new RuntimeException("해당 메뉴에 대한 권한이 없습니다.");
        }
        return menu;
    }
}