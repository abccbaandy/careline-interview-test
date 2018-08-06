package com.careline.interview.test.mission3.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ReqRegisterVo {
    @NotBlank
    @Email(message = "不合法")
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
}
