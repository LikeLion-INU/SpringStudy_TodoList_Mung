package com.example.todolist.service.member;

import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.member.MemberResponseDTO;
import com.example.todolist.entity.member.Member;
import com.example.todolist.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public MemberResponseDTO.MemberJoinDTO join(MemberRequestDTO.MemberJoinDTO memberJoinDTO) {
        try {
            log.info("[MemberServiceImpl] join");
            Member member = memberJoinDTO.toEntity();

            Optional<Member> optionalFindMember = memberRepository.findByMemberEmail(member.getMemberEmail());

            if(optionalFindMember.isPresent()) {
                // 중복 이메일 발생
                log.info("[ERROR] 중복 이메일 입니다.");
                return null; // 알아보기 쉽게 null로 일단 하겠습니다!
            }

            memberRepository.save(member); // 중복 이메일이 없다면 DB에 저장하기.

            return new MemberResponseDTO.MemberJoinDTO(member);
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @Override
    public MemberResponseDTO.MemberLoginDTO login(MemberRequestDTO.MemberLoginDTO memberLoginDTO) {
        try {
            log.info("[MemberServiceImpl] login");
            Member loginMember = memberLoginDTO.toEntity();
            Optional<Member> optionalFindMember = memberRepository.findByMemberEmail(loginMember.getMemberEmail()); // DB에서 회원 조회

            if(!optionalFindMember.isPresent()) {
                // 존재하지 않은 이메일
                log.info("[ERROR] 존재하지 않은 이메일 입니다.");
                return null; // 알아보기 쉽게 null로 일단 하겠습니다!
            }
            else if(!Objects.equals(loginMember.getMemberPassword(), optionalFindMember.get().getMemberPassword())) {
                // 틀린 비밀번호
                log.info("[ERROR] 틀린 비밀번호 입니다.");
                System.out.println(loginMember.getMemberPassword());
                System.out.println(optionalFindMember.get().getMemberPassword());
                return null; // 알아보기 쉽게 null로 일단 하겠습니다!
            }

            return new MemberResponseDTO.MemberLoginDTO(loginMember);
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @Override
    @Transactional
    public String delete(Long memberId) {
        try {
            log.info("[MemberServiceImpl] delete");
            Optional<Member> optionalFindMember = memberRepository.findById(memberId);

            if(!optionalFindMember.isPresent()) {
                // 존재하지 않은 이메일
                log.info("[ERROR] 존재하지 않은 회원 입니다.");
                return null; // 알아보기 쉽게 null로 일단 하겠습니다!
            }

            memberRepository.deleteById(memberId); // DB에서 회원 삭제

            return "SUCCESS";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @Override
    @Transactional
    public MemberResponseDTO.MemberUpdateDTO update(MemberRequestDTO.MemberUpdateDTO memberUpdateDTO) {
        try {
            log.info("[MemberServiceImpl] update");
            Optional<Member> optionalFindMember = memberRepository.findByMemberEmail(memberUpdateDTO.getMemberEmail()); // DB에서 회원 조회

            if(!optionalFindMember.isPresent()) {
                // 존재하지 않은 이메일
                log.info("[ERROR] 존재하지 않은 이메일 입니다.");
                return null; // 알아보기 쉽게 null로 일단 하겠습니다!
            }

            // 회원 수정 과정 진행
            optionalFindMember.get().memberUpdate(memberUpdateDTO);

            return new MemberResponseDTO.MemberUpdateDTO(optionalFindMember.get());
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @Override
    public MemberResponseDTO.MemberFindOneDTO findOne(Long memberId) {
        try {
            log.info("[MemberServiceImpl] findOne");
            Optional<Member> optionalFindMember = memberRepository.findById(memberId); // DB에서 회원 조회

            if(!optionalFindMember.isPresent()) {
                // 존재하지 않은 이메일
                log.info("[ERROR] 존재하지 않은 회원 입니다.");
                return null; // 알아보기 쉽게 null로 일단 하겠습니다!
            }

            return new MemberResponseDTO.MemberFindOneDTO(optionalFindMember.get());
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @Override
    public MemberResponseDTO.MemberFindAllDTO findAll() {
        try {
            log.info("[MemberServiceImpl] findAll");
            List<Member> memberList = memberRepository.findAll();
            List<MemberResponseDTO.MemberFindOneDTO> memberFindOneDTOList = new ArrayList<>();

            for(int i = 0; i< memberList.size(); i++) {
                MemberResponseDTO.MemberFindOneDTO memberFindOneDTO = new MemberResponseDTO.MemberFindOneDTO(memberList.get(i));
                memberFindOneDTOList.add(memberFindOneDTO);
            }

            return new MemberResponseDTO.MemberFindAllDTO(memberFindOneDTOList);
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }
}
