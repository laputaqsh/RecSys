package com.sky.repos;

import com.sky.dao.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos {

    @Select("select count(*) from user_info")
    int counts();

    @Select("select * from user_info where user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_pw", property = "userPw"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    UserInfo findById(int userId);

    @Insert("insert into user_info(user_id, user_name, user_pw) values(#{userId}, #{userName}, '123456')")
    void insert(int userId, String userName);
}
