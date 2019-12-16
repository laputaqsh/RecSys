package com.sky.controller;

import com.sky.constant.ProjectConstant;
import com.sky.enums.ResultEnum;
import com.sky.form.UserForm;
import com.sky.service.UserService;
import com.sky.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request);
        if (cookie != null) {
            return new ModelAndView(ProjectConstant.redirectURL.concat("/index"));
        }
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid UserForm userForm,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        boolean b = userService.loginCheck(userForm.getUserId(), userForm.getUserPw());
        if (!b) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            map.put("url", "/recsys/index");
            return new ModelAndView("common/error");
        }

        userService.setUserId(response, userForm.getUserId());

        return new ModelAndView(ProjectConstant.redirectURL.concat("/index"));
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        userService.delUserId(request, response);

        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url", "/recsys");
        return new ModelAndView("common/success", map);
    }
}
