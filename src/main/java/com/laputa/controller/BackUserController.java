package com.laputa.controller;

import com.laputa.dao.User;
import com.laputa.dto.UserDTO;
import com.laputa.service.UserService;
import com.laputa.utils.ListUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

@RestController
@RequestMapping("/back/user")
@Slf4j
public class BackUserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size, HttpServletRequest request,
            Map<String, Object> map) {
        List<User> userList = userService.lists();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            userDTO.setId(String.valueOf(user.getId()));
            userDTO.setFols(userService.countFols(user.getId()));
            userDTO.setFans(userService.countFans(user.getId()));
            userDTOList.add(userDTO);
        }

        Page<UserDTO> userPage = ListUtil.listConvertToPage(userDTOList, PageRequest.of(page - 1, size));
        map.put("userPage", userPage);
        map.put("page", page);
        map.put("size", size);

        return new ModelAndView("user/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id", defaultValue = "0") String id, Map<String, Object> map) {
        User user = userService.findById(Integer.parseInt(id));
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setId(String.valueOf(user.getId()));
        userDTO.setFols(userService.countFols(user.getId()));
        userDTO.setFans(userService.countFans(user.getId()));
        map.put("user", userDTO);
        return new ModelAndView("user/index", map);
    }
}
