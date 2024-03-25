package com.example.todolist.controller.member;

import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.member.MemberResponseDTO;
import com.example.todolist.service.member.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String join(@ModelAttribute MemberRequestDTO.MemberJoinDTO memberJoinDTO) {
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
    public String login(@ModelAttribute MemberRequestDTO.MemberLoginDTO memberLoginDTO, HttpSession httpSession) {
        try {
            log.info("[MemberController] login");
            MemberResponseDTO.MemberLoginDTO result = memberService.login(memberLoginDTO);

            if(result == null) {
                log.info("[ERROR] MemberController login");
                return "home";
            }

            httpSession.setAttribute("memberId", result.getId());

            return "main";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }
    @GetMapping("/delete")
    public String delete() {
        return "home";
    }
    @PostMapping("/delete")
    public String delete(HttpSession httpSession) {
        try {
            log.info("[MemberController] delete");
            String result = memberService.delete((Long) httpSession.getAttribute("memberId"));

            if(result != "SUCCESS") {
                log.info("[ERROR] MemberController delete");
                return "main";
            }
            return "home";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberRequestDTO.MemberUpdateDTO memberUpdateDTO, HttpSession httpSession) {
        try {
            log.info("[MemberController] update");

            Long memberId = (Long) httpSession.getAttribute("memberId");

            MemberResponseDTO.MemberUpdateDTO result = memberService.update(memberId, memberUpdateDTO);

            if(result == null) {
                log.info("[ERROR] MemberController update");
                return "update";
            }
            return "main";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @GetMapping("/findOne")
    public String findOne(HttpSession httpSession, Model model) {
        try {
            log.info("[MemberController] findOne");
            Long memberId = (Long) httpSession.getAttribute("memberId");
            MemberResponseDTO.MemberFindOneDTO result = memberService.findOne(memberId);

            if(result == null) {
                log.info("[ERROR] MemberController findOne");
                return "main";
            }

            model.addAttribute("member", result);

            return "findOne";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        try {
            log.info("[MemberController] findAll");
            MemberResponseDTO.MemberFindAllDTO result = memberService.findAll();
            model.addAttribute("members", result);
            return "findAll";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }
}
