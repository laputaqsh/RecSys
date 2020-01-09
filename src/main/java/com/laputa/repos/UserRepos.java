package com.laputa.repos;

import java.util.List;

import com.laputa.dao.User;
import com.laputa.dao.Event;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos {

        @Select("select count(*) from user")
        int counts();

        @Select("select count(*) from relation where user_id = #{userId}")
        int countFols(int userId);

        @Select("select count(*) from relation where related_user = #{userId}")
        int countFans(int userId);

        @Select("select * from user where id = #{userId}")
        @Results({ @Result(column = "id", property = "id"), @Result(column = "uid", property = "uid"),
                        @Result(column = "name", property = "name"), @Result(column = "type", property = "type"),
                        @Result(column = "avatar", property = "avatar"),
                        @Result(column = "large_avatar", property = "largeAvatar"),
                        @Result(column = "alt", property = "alt") })
        User findById(int userId);

        @Select("select * from user")
        @Results({ @Result(column = "id", property = "id"), @Result(column = "uid", property = "uid"),
                        @Result(column = "name", property = "name"), @Result(column = "type", property = "type"),
                        @Result(column = "avatar", property = "avatar"),
                        @Result(column = "large_avatar", property = "largeAvatar"),
                        @Result(column = "alt", property = "alt") })
        List<User> lists();

        @Select("select * from user where id in (select related_user as id from relation where user_id = #{userId})")
        @Results({ @Result(column = "id", property = "id"), @Result(column = "uid", property = "uid"),
                        @Result(column = "name", property = "name"), @Result(column = "type", property = "type"),
                        @Result(column = "avatar", property = "avatar"),
                        @Result(column = "large_avatar", property = "largeAvatar"),
                        @Result(column = "alt", property = "alt") })
        List<User> findFols(int userId);

        @Select("select * from user where id in (select user_id as id from relation where related_user = #{userId})")
        @Results({ @Result(column = "id", property = "id"), @Result(column = "uid", property = "uid"),
                        @Result(column = "name", property = "name"), @Result(column = "type", property = "type"),
                        @Result(column = "avatar", property = "avatar"),
                        @Result(column = "large_avatar", property = "largeAvatar"),
                        @Result(column = "alt", property = "alt") })
        List<User> findFans(int userId);

        @Select("select * from event where id in (select event_id as id from event_user where user_type = #{userType} and user_id = #{userId})")
        @Results({ @Result(column = "id", property = "id"), @Result(column = "owner_id", property = "ownerId"),
                        @Result(column = "title", property = "title"),
                        @Result(column = "content", property = "content"),
                        @Result(column = "category", property = "category"),
                        @Result(column = "begin_time", property = "beginTime"),
                        @Result(column = "end_time", property = "endTime"),
                        @Result(column = "image", property = "image"), @Result(column = "loc_id", property = "locId"),
                        @Result(column = "loc_name", property = "locName"),
                        @Result(column = "wisher_count", property = "wisherCount"),
                        @Result(column = "has_ticket", property = "hasTicket"),
                        @Result(column = "can_invite", property = "canInvite"),
                        @Result(column = "time_str", property = "timeStr"),
                        @Result(column = "album", property = "album"),
                        @Result(column = "participant_count", property = "participantCount"),
                        @Result(column = "tags", property = "tags"),
                        @Result(column = "image_hlarge", property = "imageHlarge"),
                        @Result(column = "price_range", property = "priceRange"),
                        @Result(column = "geo", property = "geo"),
                        @Result(column = "image_lmobile", property = "imageLmobile"),
                        @Result(column = "address", property = "address") })
        List<Event> findEvents(String userType, int userId);

}
