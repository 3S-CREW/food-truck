package com.toss.foodtruck.domain.store.service;

import com.toss.foodtruck.domain.member.entity.Member;
import com.toss.foodtruck.domain.member.repository.MemberRepository;
import com.toss.foodtruck.domain.store.dto.StoreRegisterRequestDto;
import com.toss.foodtruck.domain.store.dto.StoreResponseDto;
import com.toss.foodtruck.domain.store.entity.Store;
import com.toss.foodtruck.domain.store.repository.StoreRepository;
import com.toss.foodtruck.global.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    // 가게 등록
    @Transactional
    public StoreResponseDto registerStore(Long memberId, StoreRegisterRequestDto request) {
        // 1. 회원 조회
        Member member = memberRepository.findById(memberId)
                                        .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        // 2. 이미 가게가 있는지 확인 (1인 1가게)
        if (storeRepository.findByMemberId(memberId).isPresent()) {
            throw new RuntimeException("이미 등록된 가게가 있습니다.");
        }

        // 3. 가게 이름 중복 체크 (선택사항)
        if (storeRepository.existsByName(request.getName())) {
            throw new RuntimeException("이미 사용 중인 가게 이름입니다.");
        }

        // 4. 가게 생성
        Store store = Store.builder()
                   .member(member) // 연관관계 설정
                   .name(request.getName())
                   .category(request.getCategory())
                   .introduction(request.getIntroduction())
                   .address(request.getAddress())
                   .openTime(request.getOpenTime())
                   .closeTime(request.getCloseTime())
                   .isOpen(false) // 기본은 닫힘
                   .build();

        // 5. 저장
        storeRepository.save(store);

        // 6. 사장님 권한으로 변경 (선택사항: 일반 유저가 가게를 만들면 OWNER로 승격)
        if (member.getRole() == Role.USER) {
            member.changeRole(Role.OWNER);
        }

        return StoreResponseDto.from(store);
    }

    // 내 가게 조회
    public StoreResponseDto getMyStore(Long memberId) {
        Store store = storeRepository.findByMemberId(memberId)
                                     .orElseThrow(() -> new RuntimeException("등록된 가게가 없습니다."));
        return StoreResponseDto.from(store);
    }
}