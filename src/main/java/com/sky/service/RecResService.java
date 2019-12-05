package com.sky.service;

import com.sky.dao.EventInfo;
import com.sky.dto.EventDTO;
import com.sky.repos.EventRepos;
import com.sky.repos.GroupUserRepos;
import com.sky.repos.RecResRepos;
import com.sky.repos.RegionRepos;
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
    private RegionRepos regionRepos;

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
    public void setRegionRepos(RegionRepos regionRepos) {
        this.regionRepos = regionRepos;
    }

    public List<EventDTO> findByUserId(int userId) {
        Set<Integer> groupSet = groupUserRepos.findByUserId(userId);
        Set<EventDTO> eventSet = new HashSet<>();
        for (int g : groupSet) {
            Set<Integer> eventIds = recResRepos.findByGroupId(g);
            for (int e : eventIds) {
                eventSet.add(transfer(eventRepos.findById(e)));
            }
        }
        return new ArrayList<>(eventSet);
    }

    private EventDTO transfer(EventInfo eventInfo) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventId(eventInfo.getEventId());
        eventDTO.setEventName(eventInfo.getEventName());
        eventDTO.setEventTime(randomTime(eventInfo.getEventId()));
        eventDTO.setEventContent(eventInfo.getEventContent());
        eventDTO.setHostName("Admin");
        eventDTO.setEventRegion(randomName(eventInfo.getRegionId()));
        return eventDTO;
    }

    private String randomTime(int e) {
        String[] times = {"2019-07-06 14:00", "2019-07-09 14:00", "2019-07-03 08:00", "2019-07-13 18:00", "2019-07-06 19:00", "2019-07-04 14:00", "2019-07-09 10:00", "2019-07-14 20:00", "2019-07-10 15:00", "2019-07-12 13:00"};
        return times[e % 10];
    }

    private String randomName(int r) {
        String[] names = {"朝阳区 工体北路21号永利国际", "海淀区 惠新西街南口地铁站", " 朝阳区 青年路地铁站B口", "东城区 5号线和平里北街地铁站A口附近场馆", "朝阳区 朝阳大悦城", "海淀区 韦伯时代C座", "西城区 玉桃园", "朝阳区 sk大厦", "朝阳区 北土城地铁站", "丰台区 出发集合地：北京西站"};
        return names[r % 10];
    }

}
