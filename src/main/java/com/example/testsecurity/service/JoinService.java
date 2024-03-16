package com.example.testsecurity.service;

import com.example.testsecurity.dto.RequestJoinDTO;
import com.example.testsecurity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void joinProcess(RequestJoinDTO joinDTO) {

        boolean isAlreadyUser = memberRepository.existsByUsername(joinDTO.getUsername());

        if (isAlreadyUser) {
            return;
        }

        memberRepository.save(joinDTO.toEntity(
                bCryptPasswordEncoder.encode(joinDTO.getPassword()),
                "ROLE_USER")
        );
    }

}
