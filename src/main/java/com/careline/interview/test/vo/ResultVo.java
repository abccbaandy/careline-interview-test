package com.careline.interview.test.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultVo {
    private boolean success;
    private String errorMsg;
}
