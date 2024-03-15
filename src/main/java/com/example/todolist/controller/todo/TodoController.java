package com.example.todolist.controller.todo;

import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.todo.TodoRequestDTO;
import com.example.todolist.dto.todo.TodoResponseDTO;
import com.example.todolist.entity.todo.TodoStatus;
import com.example.todolist.service.todo.TodoServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
@Slf4j
public class TodoController {
    private final TodoServiceImpl todoService;
    @PostMapping("/create")
    public String create(TodoRequestDTO.TodoCreateDTO todoCreateDTO, HttpSession httpSession) {
        try {
            log.info("[TodoController] create");
            Long memberId = (Long) httpSession.getAttribute("memberId");
            TodoResponseDTO.TodoCreateDTO result = todoService.create(memberId, todoCreateDTO);
            if(result == null) {
                log.info("[ERROR] TodoController create");
            }
            return "redirect:/todo/findAll";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @PostMapping("/update/{todoId}")
    public String update(TodoRequestDTO.TodoUpdateDTO todoUpdateDTO, @PathVariable("todoId") Long todoId, HttpSession httpSession) {
        try {
            log.info("[TodoController] update");
            Long memberId = (Long) httpSession.getAttribute("memberId");

            TodoResponseDTO.TodoUpdateDTO result = todoService.update(memberId, todoId, todoUpdateDTO);

            if(result == null) {
                log.info("[ERROR] TodoController update");
            }
            return "redirect:/todo/findAll";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @PostMapping("/toggle/{todoId}")
    public String toggle(@PathVariable("todoId") Long todoId, HttpSession httpSession) {
        try {
            log.info("[TodoController] toggle");
            Long memberId = (Long) httpSession.getAttribute("memberId");

            TodoResponseDTO.TodoToggleDTO result = todoService.toggle(memberId, todoId);

            if(result == null) {
                log.info("[ERROR] TodoController toggle");
            }
            return "redirect:/todo/findAll";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }


    @PostMapping("/delete/{todoId}")
    public String delete(@PathVariable("todoId") Long todoId, HttpSession httpSession) {
        try {
            log.info("[TodoController] delete");
            Long memberId = (Long) httpSession.getAttribute("memberId");

            String result = todoService.delete(memberId, todoId);

            if(result != "SUCCESS") {
                log.info("[ERROR] TodoController delete");
            }
            return "redirect:/todo/findAll";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @GetMapping("/findAll")
    public String findAll(HttpSession httpSession, Model model) {
        try {
            log.info("[TodoController] findAll");
            Long memberId = (Long) httpSession.getAttribute("memberId");

            TodoResponseDTO.TodoFindAllDTO result = todoService.findAll(memberId);

            if(result == null) {
                log.info("[ERROR] TodoController findAll");
            }

            model.addAttribute("todos", result);
            return "todoList";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }
}
