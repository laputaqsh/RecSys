package com.laputa.repos;

import com.laputa.dao.Event;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EventRepos {

    @Select("select count(*) from event")
    int counts();

    @Select("select * from event where id >= (select id from event limit #{start}, 1) limit #{count}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "category", property = "category"),
            @Result(column = "begin_time", property = "beginTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "image", property = "image"),
            @Result(column = "loc_id", property = "locId"),
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
            @Result(column = "address", property = "address")
    })
    List<Event> lists(int start, int count);

    @Select("select distinct id from event where title like concat('%', #{0}, '%') or content like concat('%', #{0}, '%')")
    Set<Integer> search(String searchContent);

    @Select("select * from event where category = #{category}")
    @Results({
        @Result(column = "id", property = "id"),
        @Result(column = "owner_id", property = "ownerId"),
        @Result(column = "title", property = "title"),
        @Result(column = "content", property = "content"),
        @Result(column = "category", property = "category"),
        @Result(column = "begin_time", property = "beginTime"),
        @Result(column = "end_time", property = "endTime"),
        @Result(column = "image", property = "image"),
        @Result(column = "loc_id", property = "locId"),
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
        @Result(column = "address", property = "address")
    })
    List<Event> findByCategory(String category);

    @Select("select * from event where id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "category", property = "category"),
            @Result(column = "begin_time", property = "beginTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "image", property = "image"),
            @Result(column = "loc_id", property = "locId"),
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
            @Result(column = "address", property = "address")
    })
    Event findById(int id);

    @Update("update event set content = #{content} where id = #{id}")
    void updateContentById(int id, String content);

    @Select("select * from event order by participant_count + wisher_count desc limit #{limit}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "owner_id", property = "ownerId"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "category", property = "category"),
            @Result(column = "begin_time", property = "beginTime"),
            @Result(column = "end_time", property = "endTime"),
            @Result(column = "image", property = "image"),
            @Result(column = "loc_id", property = "locId"),
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
            @Result(column = "address", property = "address")
    })
    List<Event> popular(int limit);
}
