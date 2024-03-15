package com.example.todolist.controller.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
@Slf4j
public class HomeController {
    @GetMapping()
    public String home() {
        log.info("[HomeController] home");
        return "home";
    }
}
