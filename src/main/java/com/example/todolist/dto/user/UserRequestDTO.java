package com.example.todolist.dto.user;

import com.example.todolist.entity.user.User;
import lombok.Data;

@Data
public class UserRequestDTO {
    // 회원 가입
    @Data
    public static class UserJoinDTO {
        private String userEmail; // 이메일
        private String userPassword; // 비밀번호
        private String userName; // 이름
        private String userPhone; // 전화번호

        public User toEntity() { // DTO를 User 엔티티로 변환하는 메소드
            return new User(this.userEmail, this.userPassword, this.userName, this.userPhone);
        }
    }

    // 로그인
    @Data
    public static class UserLoginDTO {
        private String userEmail; // 이메일
        private String userPassword; // 비밀번호

        public User toEntity() { // DTO를 User 엔티티로 변환하는 메소드
            return new User(this.userEmail, this.userPassword);
        }
    }
}
