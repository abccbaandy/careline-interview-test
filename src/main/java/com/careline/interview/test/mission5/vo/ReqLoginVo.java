package com.careline.interview.test.mission5.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ReqLoginVo {
    @NotBlank
    @Email(message = "email不合法")
    private String email;
    @NotBlank
    private String password;
}