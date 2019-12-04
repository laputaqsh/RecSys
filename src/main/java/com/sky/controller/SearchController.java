package com.sky.controller;

import lombok.extern.slf4j.Slf4j;
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
public class SearchController {

    @PostMapping("/search")
    public ModelAndView login(@Valid String searchContent,
                              BindingResult bindingResult,
                              Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/recsys/index");
            return new ModelAndView("common/error", map);
        }
        //TODO

        return new ModelAndView("main", map);
    }

}
