package com.laputa.controller;

import com.laputa.dao.Event;
import com.laputa.dto.EventDTO;
import com.laputa.service.EventService;
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
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (Event event : eventList) {
            EventDTO eventDTO = new EventDTO();
            BeanUtils.copyProperties(event, eventDTO);
            eventDTO.setId(String.valueOf(event.getId()));
            eventDTO.setCategory(event.getCategoryName());
            eventDTO.setTime(event.getTimeStr());
            eventDTOList.add(eventDTO);
        }

        Page<EventDTO> eventPage = ListUtil.listConvertToPage(eventDTOList, PageRequest.of(page - 1, size));
        map.put("eventPage", eventPage);
        map.put("page", page);
        map.put("size", size);

        return new ModelAndView("event/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id", defaultValue = "0") String id, Map<String, Object> map) {
        Event event = eventService.findById(Integer.parseInt(id));
        EventDTO eventDTO = new EventDTO();
        BeanUtils.copyProperties(event, eventDTO);
        eventDTO.setId(String.valueOf(event.getId()));
        eventDTO.setCategory(event.getCategoryName());
        eventDTO.setTime(event.getTimeStr());

        map.put("event", eventDTO);
        return new ModelAndView("event/index", map);
    }
}
