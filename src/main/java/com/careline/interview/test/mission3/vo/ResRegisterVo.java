package com.careline.interview.test.mission3.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResRegisterVo {
    private String memberId;
    private String msg;
}
