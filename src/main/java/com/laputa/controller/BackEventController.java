package com.laputa.controller;

import com.laputa.dao.Event;
import com.laputa.service.EventService;
import com.laputa.utils.ListUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

@RestController
@RequestMapping("/back/event")
@Slf4j
public class BackEventController {

    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size, HttpServletRequest request,
            Map<String, Object> map) {
        List<Event> eventList = eventService.lists(0, 100);

        Page<Event> eventPage = ListUtil.listConvertToPage(eventList, PageRequest.of(page - 1, size));
        map.put("eventPage", eventPage);
        map.put("page", page);
        map.put("size", size);

        return new ModelAndView("event/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(String eventId, Map<String, Object> map) {
        int id = Integer.parseInt(eventId.replaceAll(",", ""));
        Event event = eventService.findById(id);
        map.put("event", event);
        return new ModelAndView("event/index", map);
    }
}
