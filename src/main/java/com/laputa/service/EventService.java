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

    public List<Event> lists(int locId, int start, int count) {
        return eventRepos.lists(locId, start, count);
    }

    public Event findById(int id) {
        return eventRepos.findById(id);
    }

    public Set<Integer> search(String searchContent) {
        return eventRepos.search(searchContent);
    }

    public void cleanDataset() {
        List<Event> eventList = eventRepos.lists(108288, 0, eventRepos.counts());
        for (Event item : eventList) {
            eventRepos.updateContentById(item.getId(), transfer(item.getContent()));
        }
    }

    private String transfer(String content) {
        return content.replaceAll("<div class=\"middle\">", "")
                .replaceAll("</div>", "")
                .replaceAll("<span class=\"pic-title\"></span>", "")
                .replaceAll("img ", "img width=\"100%\" ");
    }

}
