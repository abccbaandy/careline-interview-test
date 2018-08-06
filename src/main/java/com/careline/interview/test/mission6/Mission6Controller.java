package com.careline.interview.test.mission6;

import com.careline.interview.test.Entity.Member;
import com.careline.interview.test.mission6.vo.ReqChangePasswordVo;
import com.careline.interview.test.mission6.vo.ReqUpdateMemberVo;
import com.careline.interview.test.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/mission6")
@Slf4j
public class Mission6Controller {

    @Autowired
    UserDetailsManager userDetailsManager;

    @PostMapping("/updateProfile")
    public ResultVo updateProfile(@ModelAttribute @Valid ReqUpdateMemberVo reqUpdateMemberVo) {
        Member member = new Member();
        member.setUsername(reqUpdateMemberVo.getName());
        member.setEmail(reqUpdateMemberVo.getEmail());
        userDetailsManager.updateUser(member);
        return ResultVo.builder().success(true).build();
    }

    @PostMapping("/updatePassword")
    public ResultVo updatePassword(@ModelAttribute @Valid ReqChangePasswordVo reqChangePasswordVo) {
        userDetailsManager.changePassword(reqChangePasswordVo.getOldPassword(), reqChangePasswordVo.getNewPassword());
        return ResultVo.builder().success(true).build();
    }
}
