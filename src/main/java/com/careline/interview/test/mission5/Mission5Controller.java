package com.careline.interview.test.mission5;

import com.careline.interview.test.mission5.vo.ReqLoginVo;
import com.careline.interview.test.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mission5")
@Slf4j
public class Mission5Controller {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResultVo login(@ModelAttribute ReqLoginVo reqLoginVo) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(reqLoginVo.getEmail(), reqLoginVo.getPassword());
        try {
            authentication = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResultVo.builder().success(true).build();
        } catch (Exception e) {
            log.error("login failed ", e);
            return ResultVo.builder().success(false).errorMsg(e.getMessage()).build();
        }
    }

    @PostMapping("/logout")
    public ResultVo logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResultVo.builder().success(true).build();
    }
}
