package com.example.todolist.controller.page;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
@Slf4j
public class PageController {
    @GetMapping()
    public String home() {
        log.info("[PageController] home");
        return "home";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @GetMapping("/delete")
    public String delete() {
        return "home";
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }
}
