package com.careline.interview.test;

import com.careline.interview.test.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(value = {BindException.class})
    public ResultVo validErrorHandler(BindException e) {
        log.error("valid failed", e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        return ResultVo.builder().success(false).errorMsg(fieldError.getField() + " " + fieldError.getDefaultMessage()).build();
    }

    @ExceptionHandler(value = {Exception.class})
    public ResultVo defaultErrorHandler(Exception e) {
        log.error("default failed", e);
        return ResultVo.builder().success(false).errorMsg(e.getMessage()).build();
    }
}
