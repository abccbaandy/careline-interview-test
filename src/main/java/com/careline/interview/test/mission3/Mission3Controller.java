package com.careline.interview.test.mission3;

import com.careline.interview.test.Entity.Member;
import com.careline.interview.test.mission3.vo.ReqRegisterVo;
import com.careline.interview.test.mission3.vo.ResRegisterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mission3")
@Slf4j
public class Mission3Controller {
    @Autowired
    UserDetailsManager userDetailsManager;

    @PostMapping("/register")
    public ResRegisterVo register(@ModelAttribute @Valid ReqRegisterVo reqRegisterVo) {
        Member member = new Member();
        member.setEmail(reqRegisterVo.getEmail());
        member.setUsername(reqRegisterVo.getName());
        member.setPassword(reqRegisterVo.getPassword());
        userDetailsManager.createUser(member);
        return ResRegisterVo.builder().memberId(member.getId().toString()).build();
    }

    @ExceptionHandler(value = {BindException.class})
    public ResRegisterVo defaultErrorHandler(BindException e) {
        log.error("mission3 valid failed", e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        return ResRegisterVo.builder().msg(fieldError.getField() + " " + fieldError.getDefaultMessage()).build();
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResRegisterVo UniqueErrorHandler(DataIntegrityViolationException e) {
        log.error("mission3 sql exec failed", e);
        if (e.getMessage() != null && e.getMessage().contains("email")) {
            return ResRegisterVo.builder().msg("email已重複").build();
        } else {
            return ResRegisterVo.builder().msg(e.getMessage()).build();
        }
    }
}
