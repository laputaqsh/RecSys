package com.laputa.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import com.laputa.dao.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EventServiceTest {


    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Test
    public void update() {
        List<Event> events = eventService.search("方特");
        log.info(""+events.size());
    }

}
