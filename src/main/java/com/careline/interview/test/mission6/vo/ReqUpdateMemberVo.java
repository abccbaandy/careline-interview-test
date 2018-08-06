package com.careline.interview.test.mission6.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ReqUpdateMemberVo {
    @NotBlank
    @Email(message = "不合法")
    private String email;
    @NotBlank
    private String name;
}