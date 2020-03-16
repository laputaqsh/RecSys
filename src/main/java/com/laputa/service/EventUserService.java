package com.laputa.service;

import com.laputa.dao.EventUser;
import com.laputa.repos.EventUserRepos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class EventUserService {

    private EventUserRepos eventUserRepos;

    @Autowired
    public void setEventUserRepos(EventUserRepos eventUserRepos) {
        this.eventUserRepos = eventUserRepos;
    }

    public int counts() {
        return eventUserRepos.counts();
    }

    public List<EventUser> lists() {
        return eventUserRepos.lists();
    }

    public Boolean exists(int userId, int eventId, String type) {
        return null != eventUserRepos.exists(userId, eventId, type);
    }

    public void insert(int userId, int eventId, String type) {
        eventUserRepos.insert(userId, eventId, type);
    }

    public void delete(int userId, int eventId, String type) {
        eventUserRepos.delete(userId, eventId, type);
    }

}
