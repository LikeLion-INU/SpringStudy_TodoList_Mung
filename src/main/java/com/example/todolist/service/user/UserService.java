package com.example.todolist.service.user;

import com.example.todolist.dto.user.UserRequestDTO;
import com.example.todolist.dto.user.UserResponseDTO;

public interface UserService {
    // 회원 가입
    UserResponseDTO.UserJoinDTO join(UserRequestDTO.UserJoinDTO userJoinDTO);

    // 로그인
    UserResponseDTO.UserLoginDTO login(UserRequestDTO.UserLoginDTO userLoginDTO);

    // 회원 탈퇴
    String delete(Long userId);

    // 회원 수정
    UserResponseDTO.UserUpdateDTO update(UserRequestDTO.UserUpdateDTO userUpdateDTO);

    // 내 정보 조회
    UserResponseDTO.UserFindOneDTO findOne(Long userId);

    // 모든 회원 조회
    UserResponseDTO.UserFindAllDTO findAll();
}
