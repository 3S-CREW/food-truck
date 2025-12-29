package com.toss.foodtruck.domain.entity;

import com.toss.foodtruck.global.common.BaseTimeEntity;
import com.toss.foodtruck.global.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; // 서버 내부 관리용 ID (PK)
    // [중요] 토스 사용자 식별자 (UUID 형태)
    // 문서상 'accountId' 또는 'userKey'에 해당
    // 유니크 제약조건 필수 (한 명의 토스 유저는 하나의 계정만 가짐)

    @Column(name = "account_id", nullable = false, unique = true, length = 100)
    private String accountId;

    @Column(name = "name", nullable = false, length = 20)
    private String name; // 홍길동

    @Column(name = "phone_number", length = 20)
    private String phoneNumber; // 01012345678 (선택사항)

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role; // USER(손님), OWNER(사장님), ADMIN(관리자)

    // 회원 정보 수정 (토스 정보가 바뀌었을 때 업데이트 용도)
    public void updateInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // 사장님 권한 부여 등 권한 변경 로직
    public void changeRole(Role role) {
        this.role = role;
    }
}
