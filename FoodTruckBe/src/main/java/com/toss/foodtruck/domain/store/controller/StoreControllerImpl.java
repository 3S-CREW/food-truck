package com.toss.foodtruck.domain.store.controller;

import com.toss.foodtruck.domain.store.dto.StoreRegisterRequestDto;
import com.toss.foodtruck.domain.store.dto.StoreResponseDto;
import com.toss.foodtruck.domain.store.dto.StoreUpdateRequestDto;
import com.toss.foodtruck.domain.store.service.StoreService;
import com.toss.foodtruck.global.response.ResultCode;
import com.toss.foodtruck.global.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreControllerImpl implements StoreController {

    private final StoreService storeService;

    @Override
    @PostMapping
    public ResponseEntity<ResultResponse<StoreResponseDto>> registerStore(
        @AuthenticationPrincipal String memberId, // JWT에서 추출한 memberId (String)
        @RequestBody StoreRegisterRequestDto request
    ) {
        Long id = Long.parseLong(memberId); // String -> Long 변환
        StoreResponseDto response = storeService.registerStore(id, request);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.CREATE_STORE_SUCCESS, response));
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<ResultResponse<StoreResponseDto>> getMyStore(
        @AuthenticationPrincipal String memberId
    ) {
        Long id = Long.parseLong(memberId);
        StoreResponseDto response = storeService.getMyStore(id);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_MY_STORE_SUCCESS, response));
    }

    @Override
    @PutMapping
    public ResponseEntity<ResultResponse<StoreResponseDto>> updateStore(
        @AuthenticationPrincipal String memberId,
        @RequestBody StoreUpdateRequestDto request
    ) {
        Long id = Long.parseLong(memberId);
        StoreResponseDto response = storeService.updateStore(id, request);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_STORE_INFO_SUCCESS, response));
    }

    @Override
    @PatchMapping("/open-close")
    public ResponseEntity<ResultResponse<Boolean>> openCloseStore(
        @AuthenticationPrincipal String memberId
    ) {
        Long id = Long.parseLong(memberId);
        boolean isOpen = storeService.openCloseStore(id);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_STORE_STATUS_SUCCESS, isOpen));
    }
}