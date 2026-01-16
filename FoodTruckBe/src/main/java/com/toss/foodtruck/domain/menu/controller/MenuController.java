package com.toss.foodtruck.domain.menu.controller;

import com.toss.foodtruck.domain.menu.dto.MenuRequestDto;
import com.toss.foodtruck.domain.menu.dto.MenuResponseDto;
import com.toss.foodtruck.global.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Menu (Owner)", description = "사장님 메뉴 관리 API")
public interface MenuController {

    @Operation(summary = "메뉴 등록", description = "내 가게에 새로운 메뉴를 추가합니다.")
    ResponseEntity<ResultResponse<MenuResponseDto>> createMenu(
        @Parameter(hidden = true) String memberId,
        @RequestBody MenuRequestDto request
    );

    @Operation(summary = "내 메뉴 목록 조회", description = "내 가게에 등록된 모든 메뉴를 조회합니다.")
    ResponseEntity<ResultResponse<List<MenuResponseDto>>> getMyMenus(
        @Parameter(hidden = true) String memberId
    );

    @Operation(summary = "메뉴 수정", description = "메뉴 이름, 가격, 설명, 이미지를 수정합니다.")
    ResponseEntity<ResultResponse<MenuResponseDto>> updateMenu(
        @Parameter(hidden = true) String memberId,
        @Parameter(description = "수정할 메뉴 ID") @PathVariable Long menuId,
        @RequestBody MenuRequestDto request
    );

    @Operation(summary = "메뉴 삭제", description = "메뉴를 삭제합니다.")
    ResponseEntity<ResultResponse<String>> deleteMenu(
        @Parameter(hidden = true) String memberId,
        @Parameter(description = "삭제할 메뉴 ID") @PathVariable Long menuId
    );

    @Operation(summary = "메뉴 품절 처리 (토글)", description = "메뉴의 품절 상태를 변경합니다. (판매중 <-> 품절)")
    ResponseEntity<ResultResponse<Boolean>> toggleSoldOut(
        @Parameter(hidden = true) String memberId,
        @Parameter(description = "상태 변경할 메뉴 ID") @PathVariable Long menuId
    );
}