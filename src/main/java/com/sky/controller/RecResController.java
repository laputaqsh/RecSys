package com.sky.controller;

import com.sky.dao.EventInfo;
import com.sky.dto.EventDTO;
import com.sky.service.RecResService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/")
@Slf4j
public class RecResController {

    private RecResService service;

    @Autowired
    public void setService(RecResService service) {
        this.service = service;
    }

    @PostMapping("/rec")
    public ModelAndView recs(@Valid Integer userId,
                             Map<String, Object> map) {
        List<EventDTO> eventList = service.findByUserId(userId);
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
        return new ModelAndView("rec", map);
    }
}
