package com.laputa.repos;

import java.util.List;

import com.laputa.dao.EventUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface EventUserRepos {

        @Select("select count(*) from event_user")
        int counts();

        @Select("select * from event_user")
        @Results({ @Result(column = "id", property = "id"), @Result(column = "event_id", property = "eventId"),
                        @Result(column = "user_id", property = "userId") })
        List<EventUser> lists();

}
