package com.laputa.repos;

import java.util.List;

import com.laputa.dao.EventUser;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface EventUserRepos {

        @Select("select count(*) from event_user")
        int counts();

        @Select("select * from event_user")
        @Results({ @Result(column = "id", property = "id"), @Result(column = "user_id", property = "userId"),
                        @Result(column = "event_id", property = "eventId"),
                        @Result(column = "type", property = "type") })
        List<EventUser> lists();

        @Select("select * from event_user where user_id = #{userId} and event_id = #{eventId} and type = #{type}")
        @Results({ @Result(column = "id", property = "id"), @Result(column = "user_id", property = "userId"),
                        @Result(column = "event_id", property = "eventId"),
                        @Result(column = "type", property = "type") })
        EventUser exists(int userId, int eventId, String type);

        @Insert("insert into event_user(user_id, event_id, type) values(#{userId}, #{eventId}, #{type})")
        @Results({ @Result(column = "id", property = "id"), @Result(column = "user_id", property = "userId"),
                        @Result(column = "event_id", property = "eventId"),
                        @Result(column = "type", property = "type") })
        void insert(int userId, int eventId, String type);

        @Delete("delete from event_user where user_id = #{userId} and event_id = #{eventId} and type = #{type}")
        @Results({ @Result(column = "id", property = "id"), @Result(column = "user_id", property = "userId"),
                        @Result(column = "event_id", property = "eventId"),
                        @Result(column = "type", property = "type") })
        void delete(int userId, int eventId, String type);

}
