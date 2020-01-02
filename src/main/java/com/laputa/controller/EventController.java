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
            @RequestParam(value = "type", defaultValue = "全部") String type,
            @RequestParam(value = "date", defaultValue = "未来") String date,
            @RequestParam(value = "loca", defaultValue = "全部") String loca,
            @RequestParam(value = "start", defaultValue = "0") Integer start,
            @RequestParam(value = "count", defaultValue = "50") Integer count) {
        List<Event> eventList = eventService.lists(start, count);
        List<Event> res = new ArrayList<>();
        for (Event item : eventList) {
            if (type.equals("全部") || type.equals(item.getCategory())) {
                res.add(item);
            }
        }
        return res;
    }

    @GetMapping("/detail")
    public Event detail(@RequestParam(value = "id") Integer id) {
        return eventService.findById(id);
    }

    @GetMapping("/hots")
    public List<Event> hots(@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return eventService.popular(limit);
    }

    @GetMapping("/recs")
    public List<Event> recs(@RequestParam(value = "userId", defaultValue = "1015534") Integer id) { 
        List<Event> eventList = eventService.lists(0, 300);
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 10) {
            set.add(random.nextInt(eventList.size()));
        }
        List<Event> recList = new ArrayList<>();
        for (int i : set) {
            recList.add(eventList.get(i));
        }
        return recList;
    }
}
