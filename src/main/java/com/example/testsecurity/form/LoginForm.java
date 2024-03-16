package com.example.testsecurity.form;

import com.example.testsecurity.dto.RequestJoinDTO;
import com.example.testsecurity.dto.RequestLoginDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginForm {
    String username;
    String password;

    @Builder
    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RequestLoginDTO toRequestLoginDTO() {
        return RequestLoginDTO.builder()
                .username(username)
                .password(password)
                .build();
    }
}
