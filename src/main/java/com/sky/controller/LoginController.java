package com.sky.controller;

import com.sky.constant.CookieConstant;
import com.sky.constant.ProjectConstant;
import com.sky.constant.RedisConstant;
import com.sky.dao.UserInfo;
import com.sky.enums.ResultEnum;
import com.sky.form.UserForm;
import com.sky.service.UserService;
import com.sky.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
@Slf4j
public class LoginController {

    private UserService userService;

    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/")
    public ModelAndView index(Map<String, Object> map) {
        UserInfo user = new UserInfo();
        map.put("user", user);
        return new ModelAndView("index", map);
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid UserForm userForm,
                              HttpServletResponse response,
                              BindingResult bindingResult,
                              Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/recsys/index");
            return new ModelAndView("common/error", map);
        }
        boolean b = userService.loginCheck(userForm.getUserId(), userForm.getUserPw());
        if (!b) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            map.put("url", "/recsys/index");
            return new ModelAndView("common/error");
        }

        //设置到Redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), String.valueOf(userForm.getUserId()), expire, TimeUnit.SECONDS);

        //设置到Cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);

        map.put("userId", userForm.getUserId());
        return new ModelAndView("main");
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //清除Redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //清除Cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMsg());
        map.put("url", "/recsys/index");
        return new ModelAndView("common/success", map);
    }
}
