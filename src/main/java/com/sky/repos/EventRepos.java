package com.sky.repos;

import com.sky.dao.EventInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EventRepos {

    @Select("select count(*) from event_info")
    int counts();

    @Select("select distinct * from event_info")
    @Results({
            @Result(column = "event_id", property = "eventId"),
            @Result(column = "event_name", property = "eventName"),
            @Result(column = "region_id", property = "regionId"),
            @Result(column = "event_time", property = "eventTime"),
            @Result(column = "event_content", property = "eventContent"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    Set<EventInfo> lists();

    @Select("select * from event_info where event_id = #{eventId}")
    @Results({
            @Result(column = "event_id", property = "eventId"),
            @Result(column = "event_name", property = "eventName"),
            @Result(column = "region_id", property = "regionId"),
            @Result(column = "event_time", property = "eventTime"),
            @Result(column = "event_content", property = "eventContent"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    EventInfo findById(int eventId);

    @Insert("insert into event_info(event_id, region_id, event_name) values(#{eventId}, #{regionId}, #{eventName})")
    void insert(int eventId, int regionId, String eventName);
}
