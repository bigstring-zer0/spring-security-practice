package com.example.testsecurity.repository;

import com.example.testsecurity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUsername(String username);

    Member findByUsername(String username);
    
}
