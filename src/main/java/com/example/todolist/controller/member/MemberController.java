package com.example.todolist.controller.member;

import com.example.todolist.common.exception.Exception500;
import com.example.todolist.common.response.ApiResponse;
import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.member.MemberResponseDTO;
import com.example.todolist.service.member.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberServiceImpl memberService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberRequestDTO.MemberJoinDTO memberJoinDTO) {
        try {
            log.info("[MemberController] join");
            MemberResponseDTO.MemberJoinDTO result = memberService.join(memberJoinDTO);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member join success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?>  login(@ModelAttribute MemberRequestDTO.MemberLoginDTO memberLoginDTO, HttpSession httpSession) {
        try {
            log.info("[MemberController] login");
            MemberResponseDTO.MemberLoginDTO result = memberService.login(memberLoginDTO);

            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member login success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }
    @PostMapping("/delete")
    public ResponseEntity<?> delete(HttpSession httpSession) {
        try {
            log.info("[MemberController] delete");
            String result = memberService.delete((Long) httpSession.getAttribute("memberId"));
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member delete success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute MemberRequestDTO.MemberUpdateDTO memberUpdateDTO, HttpSession httpSession) {
        try {
            log.info("[MemberController] update");
            Long memberId = (Long) httpSession.getAttribute("memberId");
            MemberResponseDTO.MemberUpdateDTO result = memberService.update(memberId, memberUpdateDTO);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member update success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    @GetMapping("/findOne")
    public ResponseEntity<?> findOne(HttpSession httpSession) {
        try {
            log.info("[MemberController] findOne");
            Long memberId = (Long) httpSession.getAttribute("memberId");
            MemberResponseDTO.MemberFindOneDTO result = memberService.findOne(memberId);

            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member findOne success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            log.info("[MemberController] findAll");
            MemberResponseDTO.MemberFindAllDTO result = memberService.findAll();
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member findAll success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }
}
