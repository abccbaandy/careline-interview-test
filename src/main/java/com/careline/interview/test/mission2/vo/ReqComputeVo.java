package com.careline.interview.test.mission2.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ReqComputeVo {
    @NotNull
    List<Integer> numList;
}