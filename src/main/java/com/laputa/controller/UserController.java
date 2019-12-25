package com.laputa.controller;

import com.laputa.dao.User;
import com.laputa.enums.ResultEnum;
import com.laputa.service.EventService;
import com.laputa.service.UserService;
import com.laputa.utils.ResultUtil;
import com.laputa.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
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
    public ResultVO login(@RequestParam("userId") String userId) {
        User user = userService.loginCheck(userId);
        if (user == null) return ResultUtil.failure(ResultEnum.FAILURE);

        /*UserDTO userDTO = transferService.transfer(userService.findByUserId(userId));
        Set<Integer> pastEids = trainSetService.findPastEids(userDTO.getUserId());

        for (Integer eid : pastEids) {
            EventInfo tmp = eventService.findEventById(eid);
            userDTO.getUserPastEventList().add(transferService.transfer(tmp));
        }*/
        return ResultUtil.success(user);
    }
}
