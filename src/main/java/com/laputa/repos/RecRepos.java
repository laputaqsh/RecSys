package com.laputa.repos;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RecRepos {

    @Select("select count(*) from rec_res")
    int counts();

    @Select("select event_id from rec_res where group_id = #{groupId}")
    Set<Integer> findByGroupId(Integer groupId);

    @Insert("insert into rec_res(group_id, event_id) values(#{groupId}, #{eventId})")
    void insert(int groupId, int eventId);
}
