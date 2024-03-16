package com.example.testsecurity.dto;

import com.example.testsecurity.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RequestLoginDTO {
    String username;
    String password;

    @Builder
    public RequestLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Member toEntity(String password, String role) {
        return Member.builder()
                .username(this.username)
                .password(password)
                .role(role)
                .build();
    }
}
