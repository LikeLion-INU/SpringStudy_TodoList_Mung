package com.example.todolist.dto.member;

import com.example.todolist.entity.member.Member;
import lombok.Data;

@Data
public class MemberRequestDTO {
    // 회원 가입
    @Data
    public static class MemberJoinDTO {
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public Member toEntity() { // DTO를 Member 엔티티로 변환하는 메소드
            return new Member(this.memberEmail, this.memberPassword, this.memberName, this.memberPhone);
        }
    }

    // 로그인
    @Data
    public static class MemberLoginDTO {
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호

    }

    // 회원 수정
    @Data
    public static class MemberUpdateDTO {
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public Member toEntity() { // DTO를 Member 엔티티로 변환하는 메소드
            return new Member(this.memberEmail, this.memberPassword, this.memberName, this.memberPhone);
        }
    }
}
