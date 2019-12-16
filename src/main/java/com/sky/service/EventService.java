package com.sky.service;

import com.sky.dao.EventInfo;
import com.sky.repos.EventRepos;
import com.sky.repos.TestSetRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class EventService {

    private EventRepos eventRepos;
    private TestSetRepos testSetRepos;

    @Autowired
    public void setEventRepos(EventRepos eventRepos) {
        this.eventRepos = eventRepos;
    }

    @Autowired
    public void setTestSetRepos(TestSetRepos testSetRepos) {
        this.testSetRepos = testSetRepos;
    }

    public Set<EventInfo> lists() {
        return eventRepos.lists();
    }

    public EventInfo findById(int eventId) {
        return eventRepos.findById(eventId);
    }

    public Set<Integer> listCand() {
        return testSetRepos.lists();
    }

}
