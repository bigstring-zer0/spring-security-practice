package com.example.testsecurity.controller;

import com.example.testsecurity.form.JoinForm;
import com.example.testsecurity.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("joinForm", JoinForm.builder().build());
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(@ModelAttribute(name = "joinForm") JoinForm joinForm) {

        joinService.joinProcess(joinForm.toRequestJoinDTO());
        // 회원가입을 진행하면 "/login" 경로로 리다이렉트
        return "redirect:/login";
    }

}
