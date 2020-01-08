package com.laputa.controller;

import java.io.IOException;
import com.laputa.constant.ProjectConstant;
import com.laputa.dao.User;
import com.laputa.dao.Event;
import com.laputa.dto.UserDTO;
import com.laputa.enums.ResultEnum;
import com.laputa.service.UserService;
import com.laputa.utils.ResultUtil;
import com.laputa.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResultVO login(@RequestParam("userId") Integer userId, @RequestParam("userPw") String userPw) {
        long startTime = System.currentTimeMillis(); // 获取开始时间

        User user = userService.findById(userId);
        if (user == null) {
            return ResultUtil.failure(ResultEnum.FAILURE);
        }
        
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setFols(userService.countFols(userId));
        userDTO.setFans(userService.countFans(userId));
        ResultVO res = ResultUtil.success(userDTO);

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("登录用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间

        return res;
    }

    @PostMapping("/regist")
    public ResultVO regist(@RequestParam("userId") Integer userId, @RequestParam("userPw") String userPw) {
        long startTime = System.currentTimeMillis(); // 获取开始时间

        if (userService.findById(userId) != null) {
            return ResultUtil.failure(ResultEnum.FAILURE);
        }

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("注册用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间

        return ResultUtil.success();
    }

    @GetMapping("/fols")
    public ResultVO fols(@RequestParam("userId") Integer userId) {
        long startTime = System.currentTimeMillis(); // 获取开始时间


        List<User> users = userService.getFols(userId);
        if (users == null) {
            return ResultUtil.failure(ResultEnum.FAILURE);
        }
        ResultVO res = ResultUtil.success(users);

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("关注用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间

        return res;
    }

    @GetMapping("/fans")
    public ResultVO fans(@RequestParam("userId") Integer userId) {
        long startTime = System.currentTimeMillis(); // 获取开始时间

        List<User> users = userService.getFans(userId);
        if (users == null) {
            return ResultUtil.failure(ResultEnum.FAILURE);
        }
        ResultVO res = ResultUtil.success(users);

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("粉丝用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间

        return res;
    }

    @GetMapping("/collects")
    public ResultVO collects(@RequestParam("userId") Integer userId) {
        long startTime = System.currentTimeMillis(); // 获取开始时间

        List<Event> events = userService.getCollects(userId);
        if (events == null) {
            return ResultUtil.failure(ResultEnum.FAILURE);
        }
        ResultVO res = ResultUtil.success(events);

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("感兴趣用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间
        
        return res;
    }

    @GetMapping("/history")
    public ResultVO history(@RequestParam("userId") Integer userId) {
        long startTime = System.currentTimeMillis(); // 获取开始时间

        List<Event> events = userService.getHistory(userId);
        if (events == null) {
            return ResultUtil.failure(ResultEnum.FAILURE);
        }
        ResultVO res = ResultUtil.success(events);

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("要参加用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间

        return res;
    }
    
    // @GetMapping("/auth")
    // public String auth(@RequestParam("code") String code) throws IOException {
    //     String url = ProjectConstant.auth2SessionUrl + "&js_code=" + code;
    //     log.info("url: " + url);

    //     OkHttpClient client = new OkHttpClient();
    //     Request request = new Request.Builder().url(url).build();

    //     try (Response response = client.newCall(request).execute()) {
    //         return response.body().string();
    //     }
    // }
}
