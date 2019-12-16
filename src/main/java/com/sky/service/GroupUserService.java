package com.sky.service;

import com.sky.dao.GroupInfo;
import com.sky.dto.GroupDTO;
import com.sky.repos.GroupRepos;
import com.sky.repos.GroupUserRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class GroupUserService {

    private GroupRepos groupRepos;
    private GroupUserRepos groupUserRepos;
    private TransferService transferService;

    @Autowired
    public void setGroupRepos(GroupRepos groupRepos) {
        this.groupRepos = groupRepos;
    }

    @Autowired
    public void setGroupUserRepos(GroupUserRepos groupUserRepos) {
        this.groupUserRepos = groupUserRepos;
    }

    @Autowired
    public void setTransferService(TransferService transferService) {
        this.transferService = transferService;
    }

    public Set<Integer> findById(int userId) {
        return groupUserRepos.findByUserId(userId);
    }

    public List<GroupDTO> findByUserId(int userId) {
        Set<Integer> gidSet = groupUserRepos.findByUserId(userId);
        Set<GroupDTO> groupSet = new HashSet<>();
        for (int gid : gidSet) {
            GroupInfo group = groupRepos.findById(gid);
            groupSet.add(transferService.transfer(group));
        }
        return new ArrayList<>(groupSet);
    }
}
