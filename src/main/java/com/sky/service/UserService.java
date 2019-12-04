package com.sky.service;

import com.sky.dao.UserInfo;
import com.sky.repos.UserRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private UserRepos repos;

    @Autowired
    public void setRepos(UserRepos repos) {
        this.repos = repos;
    }

    public boolean loginCheck(String userId, String userPw) {
        UserInfo userInfo = repos.findById(Integer.parseInt(userId));
        return userInfo.getUserPw().equals(userPw);
    }

}
