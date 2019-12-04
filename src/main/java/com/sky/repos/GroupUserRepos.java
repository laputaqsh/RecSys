package com.sky.repos;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupUserRepos {

    @Select("select distinct user_id from group_user where group_id = #{groupId}")
    List<Integer> findByGroupId(int groupId);

    @Insert("insert into group_user(group_id, user_id) values(#{groupId}, #{userId})")
    void insert(int groupId, int userId);
}
