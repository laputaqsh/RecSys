package com.sky.repos;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RecResRepos {

    @Select("select distinct event_id from rec_res")
    List<Integer> lists();

    @Select("select distinct event_id from rec_res where group_id = #{groupId}")
    Set<Integer> findByGroupId(int groupId);

    @Insert("insert into rec_res(group_id, event_id) values(#{groupId}, #{eventId})")
    void insert(int groupId, int eventId);
}
