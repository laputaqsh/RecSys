package com.sky.service;

import com.sky.dao.Model;
import com.sky.repos.RecResRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecResService {

    private RecResRepos repos;


    @Autowired
    public void setRepos(RecResRepos repos) {
        this.repos = repos;
    }

}
