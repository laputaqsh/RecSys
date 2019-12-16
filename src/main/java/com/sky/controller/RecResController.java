package com.sky.controller;

import com.sky.dto.EventDTO;
import com.sky.service.RecResService;
import com.sky.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/")
@Slf4j
public class RecResController {

    private UserService userService;
    private RecResService recResService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRecResService(RecResService recResService) {
        this.recResService = recResService;
    }

    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request, Map<String, Object> map) {
        int userId = userService.getUserId(request);
        List<EventDTO> eventList = recResService.findByUserId(userId);
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 5) {
            set.add(random.nextInt(eventList.size()));
        }
        List<EventDTO> recList = new ArrayList<>();
        for (int i : set) {
            recList.add(eventList.get(i));
        }
        map.put("recList", recList);
        return new ModelAndView("index", map);
    }
}
