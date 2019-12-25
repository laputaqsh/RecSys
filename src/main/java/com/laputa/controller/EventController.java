package com.laputa.controller;

import com.laputa.dao.Event;
import com.laputa.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {

    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/list")
    public List<Event> list(@RequestParam(value = "locId", defaultValue = "108288") Integer locId,
            @RequestParam(value = "start", defaultValue = "0") Integer start,
                                         @RequestParam(value = "count", defaultValue = "50") Integer count) {
        return eventService.lists(locId, start, count);
    }

    @GetMapping("/detail")
    public Event detail(@RequestParam(value = "id") Integer id) {
        return eventService.findById(id);
    }

    /*@GetMapping("/recs")
    public ResultVO recs(@RequestParam(value = "userId", defaultValue = "") String userId) {
        List<Event> eventList = eventService.lists();
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 5) {
            set.add(random.nextInt(eventList.size()));
        }
        List<Event> recList = new ArrayList<>();
        for (int i : set) {
            recList.add(eventList.get(i));
        }

        return ResultUtil.success(recList);
    }*/
}
