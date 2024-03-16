package com.example.testsecurity.controller;

import com.example.testsecurity.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", LoginForm.builder().build());
        return "login";
    }
}
