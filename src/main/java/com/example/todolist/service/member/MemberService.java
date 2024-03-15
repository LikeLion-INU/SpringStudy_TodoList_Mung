package com.example.todolist.service.member;

import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.member.MemberResponseDTO;

public interface MemberService {
    // 회원 가입
    MemberResponseDTO.MemberJoinDTO join(MemberRequestDTO.MemberJoinDTO memberJoinDTO);

    // 로그인
    MemberResponseDTO.MemberLoginDTO login(MemberRequestDTO.MemberLoginDTO memberLoginDTO);

    // 회원 탈퇴
    String delete(Long userId);

    // 회원 수정
    MemberResponseDTO.MemberUpdateDTO update(MemberRequestDTO.MemberUpdateDTO memberUpdateDTO);

    // 내 정보 조회
    MemberResponseDTO.MemberFindOneDTO findOne(Long memberId);

    // 모든 회원 조회
    MemberResponseDTO.MemberFindAllDTO findAll();
}
