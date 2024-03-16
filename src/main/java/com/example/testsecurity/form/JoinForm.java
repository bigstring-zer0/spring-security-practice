package com.example.testsecurity.form;

import com.example.testsecurity.dto.RequestJoinDTO;
import com.example.testsecurity.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JoinForm {
    String username;
    String password;

    @Builder
    public JoinForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RequestJoinDTO toRequestJoinDTO() {
        return RequestJoinDTO.builder()
                .username(username)
                .password(password)
                .build();
    }
}
