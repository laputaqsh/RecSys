package com.sky.handler;

import com.sky.constant.ProjectConstant;
import com.sky.exception.AuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AuthorizeExceptionHandler {

    //拦截、跳转
    @ExceptionHandler(value = AuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView(ProjectConstant.redirectURL);
        //TODO
    }
}
