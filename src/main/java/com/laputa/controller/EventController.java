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
            @RequestParam(value = "count", defaultValue = "100") Integer count) {

        long startTime = System.currentTimeMillis(); // 获取开始时间

        List<Event> eventList = eventService.lists(start, count);
        List<Event> res = new ArrayList<>();
        for (Event item : eventList) {
            if (type.equals("全部") || type.equals(item.getCategory())) {
                res.add(item);
            }
        }

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("展示全部活动用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间

        return res;
    }

    @GetMapping("/detail")
    public Event detail(@RequestParam(value = "id") Integer id) {
        long startTime = System.currentTimeMillis(); // 获取开始时间

        Event event = eventService.findById(id);

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("展示活动详情用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间
        return event;
    }

    @GetMapping("/hots")
    public List<Event> hots(@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        long startTime = System.currentTimeMillis(); // 获取开始时间

        List<Event> events = eventService.popular(limit);

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("展示热门活动用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间
        return events;
    }

    @GetMapping("/recs")
    public List<Event> recs(@RequestParam(value = "userId", defaultValue = "1015534") Integer id) {
        long startTime = System.currentTimeMillis(); // 获取开始时间

        List<Event> eventList = eventService.lists(0, 100);
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 10) {
            set.add(random.nextInt(eventList.size()));
        }
        List<Event> recList = new ArrayList<>();
        for (int i : set) {
            recList.add(eventList.get(i));
        }

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("展示推荐活动用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间
        
        return recList;
    }

    @GetMapping("/search")
    public List<Event> search(@RequestParam(value = "keyWord", defaultValue = "") String keyWord) {
        long startTime = System.currentTimeMillis(); // 获取开始时间

        List<Event> events = eventService.search(keyWord);

        long endTime = System.currentTimeMillis(); // 获取结束时间
        log.info("展示搜索活动用时：" + (endTime - startTime) + "ms"); // 输出程序运行时间
        
        return events;
    }
}
