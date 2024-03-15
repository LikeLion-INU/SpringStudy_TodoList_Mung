package com.example.todolist.dto.member;

import com.example.todolist.entity.member.Member;
import lombok.Data;

import java.util.List;

@Data
public class MemberResponseDTO {
    // 회원 가입
    @Data
    public static class MemberJoinDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberJoinDTO(Member member) {
            this.Id = member.getId();
            this.memberEmail = member.getMemberEmail();
            this.memberPassword = member.getMemberPassword();
            this.memberName = member.getMemberName();
        }
    }

    // 로그인
    @Data
    public static class MemberLoginDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberLoginDTO(Member member) {
            this.Id = member.getId();
            this.memberEmail = member.getMemberEmail();
            this.memberPassword = member.getMemberPassword();
            this.memberName = member.getMemberName();
        }
    }

    // 회원 탈퇴
    @Data
    public static class MemberDeleteDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberDeleteDTO(Member member) {
            this.Id = member.getId();
            this.memberEmail = member.getMemberEmail();
            this.memberPassword = member.getMemberPassword();
            this.memberName = member.getMemberName();
        }
    }

    // 회원 수정
    @Data
    public static class MemberUpdateDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberUpdateDTO(Member member) {
            this.Id = member.getId();
            this.memberEmail = member.getMemberEmail();
            this.memberPassword = member.getMemberPassword();
            this.memberName = member.getMemberName();
        }
    }

    // 내 정보 조회
    @Data
    public static class MemberFindOneDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberFindOneDTO(Member member) {
            this.Id = member.getId();
            this.memberEmail = member.getMemberEmail();
            this.memberPassword = member.getMemberPassword();
            this.memberName = member.getMemberName();
            this.memberPhone = member.getMemberPhone();
        }
    }

    // 모든 회원 조회
    @Data
    public static class MemberFindAllDTO {
        private List<MemberFindOneDTO> memberList;

        public MemberFindAllDTO(List<MemberFindOneDTO> memberFindOneDTOList) {
            this.memberList = memberFindOneDTOList;
        }
    }
}
