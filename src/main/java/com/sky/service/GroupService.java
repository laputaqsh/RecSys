package com.sky.service;

import com.sky.dao.GroupInfo;
import com.sky.repos.GroupRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class GroupService {

    private GroupRepos groupRepos;

    @Autowired
    public void setGroupRepos(GroupRepos groupRepos) {
        this.groupRepos = groupRepos;
    }

    public Set<GroupInfo> lists() {
        return groupRepos.lists();
    }

    public GroupInfo findById(int groupId) {
        return groupRepos.findById(groupId);
    }
}
