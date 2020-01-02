package com.laputa.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.laputa.constant.ProjectConstant;
import com.laputa.dao.User;
import com.laputa.dto.UserDTO;
import com.laputa.enums.ResultEnum;
import com.laputa.service.EventService;
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

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private UserService userService;

    private EventService eventService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/login")
    public ResultVO login(@RequestParam("userId") Integer userId, @RequestParam("userPw") String userPw) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResultUtil.failure(ResultEnum.FAILURE);
        }
        List<Integer> fols = userService.getFols(userId);
        List<Integer> fans = userService.getFans(userId);
        
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setFols(fols);
        userDTO.setFans(fans);
        return ResultUtil.success(userDTO);
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
