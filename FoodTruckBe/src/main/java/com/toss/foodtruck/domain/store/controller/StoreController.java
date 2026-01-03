package com.toss.foodtruck.domain.store.controller;

import com.toss.foodtruck.domain.store.dto.StoreRegisterRequestDto;
import com.toss.foodtruck.domain.store.dto.StoreResponseDto;
import com.toss.foodtruck.domain.store.dto.StoreUpdateRequestDto;
import com.toss.foodtruck.global.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Store", description = "가게(푸드트럭) 관련 API")
public interface StoreController {

    @Operation(summary = "가게 등록", description = "사장님이 자신의 푸드트럭 가게를 등록합니다.")
    ResponseEntity<ResultResponse<StoreResponseDto>> registerStore(
        @Parameter(hidden = true) String memberId, // @AuthenticationPrincipal로 받음
        @RequestBody StoreRegisterRequestDto request
    );

    @Operation(summary = "내 가게 조회", description = "로그인한 사장님의 가게 정보를 조회합니다.")
    ResponseEntity<ResultResponse<StoreResponseDto>> getMyStore(
        @Parameter(hidden = true) String memberId
    );

    @Operation(summary = "가게 정보 수정", description = "가게 이름, 소개, 시간 등을 수정합니다.")
    ResponseEntity<ResultResponse<StoreResponseDto>> updateStore(
        @Parameter(hidden = true) String memberId,
        @RequestBody StoreUpdateRequestDto request
    );

    @Operation(summary = "영업 시작/종료", description = "가게의 영업 상태를 변경합니다. (Open <-> Closed)")
    ResponseEntity<ResultResponse<Boolean>> openCloseStore(
        @Parameter(hidden = true) String memberId
    );
}