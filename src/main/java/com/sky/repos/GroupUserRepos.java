package com.sky.repos;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GroupUserRepos {

    @Select("select distinct user_id from group_user where group_id = #{groupId}")
    Set<Integer> findByGroupId(int groupId);

    @Select("select distinct group_id from group_user where user_id = #{userId}")
    Set<Integer> findByUserId(int userId);

    @Insert("insert into group_user(group_id, user_id) values(#{groupId}, #{userId})")
    void insert(int groupId, int userId);
}
