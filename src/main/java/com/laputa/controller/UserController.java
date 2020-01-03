package com.laputa.controller;

import java.io.IOException;
import com.laputa.constant.ProjectConstant;
import com.laputa.dao.User;
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
        User user = userService.findById(userId);
        if (user == null) {
            return ResultUtil.failure(ResultEnum.FAILURE);
        }
        
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setFols(userService.countFols(userId));
        userDTO.setFans(userService.countFans(userId));
        return ResultUtil.success(userDTO);
    }

    @GetMapping("/fols")
    public ResultVO fols(@RequestParam("userId") Integer userId) {
        List<User> users = userService.getFols(userId);
        if (users == null) {
            return ResultUtil.failure(ResultEnum.FAILURE);
        }
        return ResultUtil.success(users);
    }

    @GetMapping("/fans")
    public ResultVO fans(@RequestParam("userId") Integer userId) {
        List<User> users = userService.getFans(userId);
        if (users == null) {
            return ResultUtil.failure(ResultEnum.FAILURE);
        }
        return ResultUtil.success(users);
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
