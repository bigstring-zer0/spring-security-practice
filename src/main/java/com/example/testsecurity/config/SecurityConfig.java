package com.example.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 특정 경로에 대한 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()

                );

        http
                .formLogin((auth) -> auth.loginPage("/login") // 로그인 페이지 설정, 로그인 하지 않은 유저가 특정 페이지에 대해 권한이 없으면 자동으로 로그인 페이지로 리다이렉션
                        .loginProcessingUrl("/loginProc") // 프론트단에서 로그인 정보를 "/loginProc" 넘기게 되면 해당 경로로 스프링 시큐리티가 오픈되어서 스프링 시큐리티가 넘겨받아 자동으로 처리해준다.
                        .permitAll()
                );

        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));

//        http    // 스프링 시큐리티에는 csrf 설정이 자동으로 되어있기 때문에, 일시적으로 csrf 토큰을 넘기지 않는다.
//                .csrf((auth) -> auth.disable());


        http    // 하나의 아이디에 대해서 다중 로그인에 대한 처리
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                );

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation()
                        .changeSessionId()
                );

        return http.build();
    }
}
