package com.laputa.repos;

import com.laputa.dao.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos {

    @Select("select count(*) from user")
    int counts();

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "name", property = "name"),
            @Result(column = "type", property = "type"),
            @Result(column = "avatar", property = "avatar"),
            @Result(column = "large_avatar", property = "largeAvatar"),
            @Result(column = "alt", property = "alt")
    })
    User findById(int id);
}
