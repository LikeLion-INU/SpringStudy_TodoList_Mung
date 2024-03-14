package com.example.todolist.dto.user;

import com.example.todolist.entity.user.User;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    // 회원 가입
    @Data
    public static class UserJoinDTO {
        private Long Id; // 고유 식별자
        private String userEmail; // 이메일
        private String userPassword; // 비밀번호
        private String userName; // 이름
        private String userPhone; // 전화번호

        public UserJoinDTO(User user) {
            this.Id = user.getId();
            this.userEmail = user.getUserEmail();
            this.userPassword = user.getUserPassword();
            this.userName = user.getUserName();
        }
    }

    // 로그인
    @Data
    public static class UserLoginDTO {
        private Long Id; // 고유 식별자
        private String userEmail; // 이메일
        private String userPassword; // 비밀번호
        private String userName; // 이름
        private String userPhone; // 전화번호

        public UserLoginDTO(User user) {
            this.Id = user.getId();
            this.userEmail = user.getUserEmail();
            this.userPassword = user.getUserPassword();
            this.userName = user.getUserName();
        }
    }

    // 회원 탈퇴
    @Data
    public static class UserDeleteDTO {
        private Long Id; // 고유 식별자
        private String userEmail; // 이메일
        private String userPassword; // 비밀번호
        private String userName; // 이름
        private String userPhone; // 전화번호

        public UserDeleteDTO(User user) {
            this.Id = user.getId();
            this.userEmail = user.getUserEmail();
            this.userPassword = user.getUserPassword();
            this.userName = user.getUserName();
        }
    }

    // 회원 수정
    @Data
    public static class UserUpdateDTO {
        private Long Id; // 고유 식별자
        private String userEmail; // 이메일
        private String userPassword; // 비밀번호
        private String userName; // 이름
        private String userPhone; // 전화번호

        public UserUpdateDTO(User user) {
            this.Id = user.getId();
            this.userEmail = user.getUserEmail();
            this.userPassword = user.getUserPassword();
            this.userName = user.getUserName();
        }
    }

    // 내 정보 조회
    @Data
    public static class UserFindOneDTO {
        private Long Id; // 고유 식별자
        private String userEmail; // 이메일
        private String userPassword; // 비밀번호
        private String userName; // 이름
        private String userPhone; // 전화번호

        public UserFindOneDTO(User user) {
            this.Id = user.getId();
            this.userEmail = user.getUserEmail();
            this.userPassword = user.getUserPassword();
            this.userName = user.getUserName();
        }
    }

    // 모든 회원 조회
    @Data
    public static class UserFindAllDTO {
        private List<UserFindOneDTO> userList;
    }
}
