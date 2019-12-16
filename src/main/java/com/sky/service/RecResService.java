package com.sky.service;

import com.sky.dto.EventDTO;
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
    private TransferService transferService;

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

    @Autowired
    public void setTransferService(TransferService transferService) {
        this.transferService = transferService;
    }

    public List<EventDTO> findByUserId(int userId) {
        Set<Integer> gidSet = groupUserRepos.findByUserId(userId);
        Set<EventDTO> eventSet = new HashSet<>();
        for (int gid : gidSet) {
            Set<Integer> eventIds = recResRepos.findByGroupId(gid);
            for (int e : eventIds) {
                eventSet.add(transferService.transfer(eventRepos.findById(e)));
            }
        }
        return new ArrayList<>(eventSet);
    }

    public EventDTO findByEventId(int eventId) {
        return transferService.transfer(eventRepos.findById(eventId));
    }

}
