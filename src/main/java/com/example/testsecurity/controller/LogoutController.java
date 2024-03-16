package com.example.testsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    // 원래 csrf 설정이 활성화 되어있으면 post 방식으로만 logout을 할수 있지만
    // SecurityConfig 내부에 따로 logoutProcess() 코드를 작성해주고 아래와 같이 코드를 작성하면,
    // csrf 설정이 활성화 되어 있더라도 get 방식으로 로그아웃이 가능하다.
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }
}
