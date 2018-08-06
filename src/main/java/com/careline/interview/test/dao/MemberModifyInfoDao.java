package com.careline.interview.test.dao;

import com.careline.interview.test.Entity.MemberModifyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberModifyInfoDao extends JpaRepository<MemberModifyInfo, UUID> {

}