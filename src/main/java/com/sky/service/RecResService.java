package com.sky.service;

import com.sky.dao.EventInfo;
import com.sky.repos.EventRepos;
import com.sky.repos.GroupUserRepos;
import com.sky.repos.RecResRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RecResService {

    private EventRepos eventRepos;
    private RecResRepos recResRepos;
    private GroupUserRepos groupUserRepos;

    @Autowired
    public void setEventRepos(EventRepos eventRepos) {
        this.eventRepos = eventRepos;
    }

    @Autowired
    public void setRecResRepos(RecResRepos recResRepos) {
        this.recResRepos = recResRepos;
    }

    @Autowired
    public void setGroupUserRepos(GroupUserRepos groupUserRepos) {
        this.groupUserRepos = groupUserRepos;
    }

    public List<EventInfo> findByUserId(int userId) {
        Set<Integer> groupSet = groupUserRepos.findByUserId(userId);
        log.info("Group num: " + groupSet.size());
        Set<EventInfo> eventSet = new HashSet<>();
        for (int g : groupSet) {
            Set<Integer> eventIds = recResRepos.findByGroupId(g);
            for (int e : eventIds) {
                eventSet.add(eventRepos.findById(e));
            }
        }
        log.info("Event num: " + eventSet.size());
        return new ArrayList<>(eventSet);
    }

}
