package com.careline.interview.test.mission4.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MemberListVo {
    private List<MemberVo> memberList;
}
