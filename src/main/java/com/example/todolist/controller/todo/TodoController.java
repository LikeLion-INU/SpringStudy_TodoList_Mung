package com.example.todolist.controller.todo;

import com.example.todolist.common.exception.Exception500;
import com.example.todolist.common.response.ApiResponse;
import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.todo.TodoRequestDTO;
import com.example.todolist.dto.todo.TodoResponseDTO;
import com.example.todolist.entity.todo.TodoStatus;
import com.example.todolist.service.todo.TodoServiceImpl;
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
@RequestMapping("/todo")
@Slf4j
public class TodoController {
    private final TodoServiceImpl todoService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute TodoRequestDTO.TodoCreateDTO todoCreateDTO, HttpSession httpSession) {
        try {
            log.info("[TodoController] create");
            Long memberId = (Long) httpSession.getAttribute("memberId");
            TodoResponseDTO.TodoCreateDTO result = todoService.create(memberId, todoCreateDTO);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "todo create success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    @PostMapping("/update/{todoId}")
    public ResponseEntity<?> update(@ModelAttribute TodoRequestDTO.TodoUpdateDTO todoUpdateDTO, @PathVariable("todoId") Long todoId, HttpSession httpSession) {
        try {
            log.info("[TodoController] update");
            Long memberId = (Long) httpSession.getAttribute("memberId");
            TodoResponseDTO.TodoUpdateDTO result = todoService.update(memberId, todoId, todoUpdateDTO);

            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "todo create success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    @PostMapping("/toggle/{todoId}")
    public ResponseEntity<?> toggle(@PathVariable("todoId") Long todoId, HttpSession httpSession) {
        try {
            log.info("[TodoController] toggle");
            Long memberId = (Long) httpSession.getAttribute("memberId");
            TodoResponseDTO.TodoToggleDTO result = todoService.toggle(memberId, todoId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "todo create success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }


    @PostMapping("/delete/{todoId}")
    public ResponseEntity<?> delete(@PathVariable("todoId") Long todoId, HttpSession httpSession) {
        try {
            log.info("[TodoController] delete");
            Long memberId = (Long) httpSession.getAttribute("memberId");
            String result = todoService.delete(memberId, todoId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "todo create success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(HttpSession httpSession) {
        try {
            log.info("[TodoController] findAll");
            Long memberId = (Long) httpSession.getAttribute("memberId");
            TodoResponseDTO.TodoFindAllDTO result = todoService.findAll(memberId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "todo create success", result));
        } catch (Exception500 e) {
            log.info("[ERROR] Exception500");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }
}
