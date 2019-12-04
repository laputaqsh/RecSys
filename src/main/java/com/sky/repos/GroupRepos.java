package com.sky.repos;

import com.sky.dao.GroupInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepos {

    @Select("select count(*) from group_info")
    int counts();

    @Select("select * from group_info where group_id = #{groupId}")
    @Results({
            @Result(column = "group_id", property = "groupId"),
            @Result(column = "group_name", property = "groupName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    GroupInfo findById(int groupId);

    @Insert("insert into group_info(group_id, group_name) values(#{groupId}, #{groupName})")
    void insert(int groupId, String groupName);
}
