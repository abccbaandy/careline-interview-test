package com.careline.interview.test.dao;

import com.careline.interview.test.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberDao extends JpaRepository<Member, UUID> {
    Optional<Member> findByEmail(String email);
}