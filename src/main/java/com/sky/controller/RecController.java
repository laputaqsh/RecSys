package com.sky.controller;

import com.sky.dao.Model;
import com.sky.service.RecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/")
@Slf4j
public class RecController {

    private RecService service;

    @Autowired
    public void setService(RecService service) {
        this.service = service;
    }

    @PostMapping("/rec")
    public ModelAndView recs(@Valid Integer groupId,
                             Map<String, Object> map) {
        return new ModelAndView("rec", map);
    }
}
