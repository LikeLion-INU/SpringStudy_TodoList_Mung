package com.example.todolist.controller.member;

import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.member.MemberResponseDTO;
import com.example.todolist.service.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberServiceImpl memberService;

    @GetMapping("/join")
    public String join() {
        return "join";
    }
    @PostMapping("/join")
    public String join(MemberRequestDTO.MemberJoinDTO memberJoinDTO) {
        try {
            log.info("[MemberController] join");

            MemberResponseDTO.MemberJoinDTO result = memberService.join(memberJoinDTO);

            if(result == null) {
                log.info("[ERROR] MemberController join");
                return "join";
            }
            System.out.println(result);

            return "home";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @PostMapping("/login")
    public String login(MemberRequestDTO.MemberLoginDTO memberLoginDTO) {
        try {
            log.info("[MemberController] login");
            MemberResponseDTO.MemberLoginDTO result = memberService.login(memberLoginDTO);

            if(result == null) {
                log.info("[ERROR] MemberController login");
                return "home";
            }
            return "main";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @DeleteMapping("/delete/{memberId}")
    public String delete(@PathVariable Long memberId) {
        try {
            log.info("[MemberController] delete");
            return "delete";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @PutMapping("/update/{memberId}")
    public String update(@PathVariable Long memberId) {
        try {
            log.info("[MemberController] update");
            return "update";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @GetMapping("/findOne/{memberId}")
    public String findOne(@PathVariable Long memberId) {
        try {
            log.info("[MemberController] findOne");
            return "findOne";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @GetMapping("/findAll")
    public String findAll() {
        try {
            log.info("[MemberController] findAll");
            return "findAll";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }
}
