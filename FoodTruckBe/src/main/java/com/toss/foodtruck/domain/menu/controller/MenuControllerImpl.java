package com.toss.foodtruck.domain.menu.controller;

import com.toss.foodtruck.domain.menu.dto.MenuRequestDto;
import com.toss.foodtruck.domain.menu.dto.MenuResponseDto;
import com.toss.foodtruck.domain.menu.service.MenuService;
import com.toss.foodtruck.global.response.ResultCode;
import com.toss.foodtruck.global.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menus")
public class MenuControllerImpl implements MenuController {

    private final MenuService menuService;

    @Override
    @PostMapping
    public ResponseEntity<ResultResponse<MenuResponseDto>> createMenu(
        @AuthenticationPrincipal String memberId,
        @RequestBody MenuRequestDto request) {

        MenuResponseDto response = menuService.createMenu(Long.parseLong(memberId), request);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.CREATE_MENU_SUCCESS, response));
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<ResultResponse<List<MenuResponseDto>>> getMyMenus(
        @AuthenticationPrincipal String memberId) {

        List<MenuResponseDto> response = menuService.getMyMenus(Long.parseLong(memberId));
        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_MENU_LIST_SUCCESS, response));
    }

    @Override
    @PutMapping("/{menuId}")
    public ResponseEntity<ResultResponse<MenuResponseDto>> updateMenu(
        @AuthenticationPrincipal String memberId,
        @PathVariable Long menuId,
        @RequestBody MenuRequestDto request) {

        MenuResponseDto response = menuService.updateMenu(Long.parseLong(memberId), menuId, request);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_MENU_SUCCESS, response));
    }

    @Override
    @DeleteMapping("/{menuId}")
    public ResponseEntity<ResultResponse<String>> deleteMenu(
        @AuthenticationPrincipal String memberId,
        @PathVariable Long menuId) {

        menuService.deleteMenu(Long.parseLong(memberId), menuId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.DELETE_MENU_SUCCESS, "메뉴가 삭제되었습니다."));
    }

    @Override
    @PatchMapping("/{menuId}/sold-out")
    public ResponseEntity<ResultResponse<Boolean>> toggleSoldOut(
        @AuthenticationPrincipal String memberId,
        @PathVariable Long menuId) {

        boolean isSoldOut = menuService.toggleSoldOut(Long.parseLong(memberId), menuId);
        return ResponseEntity.ok(ResultResponse.of(ResultCode.TOGGLE_MENU_SOLDOUT_SUCCESS, isSoldOut));
    }
}