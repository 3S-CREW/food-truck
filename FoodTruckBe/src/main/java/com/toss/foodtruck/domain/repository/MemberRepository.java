package com.toss.foodtruck.domain.repository;

import com.toss.foodtruck.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // [로그인 핵심 기능]
    // 토스에서 받은 accountId가 우리 DB에 있는지 확인
    Optional<Member> findByAccountId(String accountId);

    // 중복 가입 방지용
    boolean existsByAccountId(String accountId);
}
