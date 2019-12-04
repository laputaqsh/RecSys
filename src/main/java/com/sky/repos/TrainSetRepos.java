package com.sky.repos;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public interface TrainSetRepos {

    @Select("select distinct event_id from train_set")
    List<Integer> lists();

    @Select("select distinct event_id from train_set where group_id = #{groupId}")
    Set<Integer> findByGroupId(int groupId);

    @Insert("insert into train_set(group_id, event_id) values(#{groupId}, #{eventId})")
    void insert(int groupId, int eventId);
}
