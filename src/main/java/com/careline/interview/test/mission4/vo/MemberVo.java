package com.careline.interview.test.mission4.vo;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MemberVo {
    private UUID memberId;

    private String name;

    private String email;
}
