package com.laputa.service;

import com.laputa.dao.Event;
import com.laputa.repos.EventRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EventService {

    private EventRepos eventRepos;

    @Autowired
    public void setEventRepos(EventRepos eventRepos) {
        this.eventRepos = eventRepos;
    }

    public int counts() {
        return eventRepos.counts();
    }

    public void update(int id, String content) {
        eventRepos.updateContentById(id, content);
    }

    public List<Event> lists(int start, int count) {
        return eventRepos.lists(start, count);
    }

    public Event findById(int id) {
        return eventRepos.findById(id);
    }

    public List<Event> search(String keyWord) {
        List<Event> res = eventRepos.search(keyWord);
        return res;
    }

    public List<Event> findByCategory(String category) {
        return eventRepos.findByCategory(category);
    }

    public List<Event> popular(int limit) {
        return eventRepos.popular(limit);
    }

}
