package com.laputa.repos;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EventUserRepos {

    @Select("select count(*) from event_user")
    int counts();

    @Select("select * from event_user where event_id = #{eventId}")
    @Results({
            @Result(column = "event_id", property = "eventId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_type", property = "userType")
    })
    List<Integer> findByEventId(int eventId);

    @Select("select distinct event_id from event_user where event_id = #{userId}")
    Set<Integer> findByUserId(int userId);

}
