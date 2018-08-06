package com.careline.interview.test.mission6.vo;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

@Data
public class ReqChangePasswordVo {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
    @NotBlank
    private String newPasswordConfirm;

    @AssertTrue(message = "輸入的新密碼不一致")
    private boolean isPasswordEqual() {
        return newPassword.equals(newPasswordConfirm);
    }
}