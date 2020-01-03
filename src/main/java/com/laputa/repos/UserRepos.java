package com.laputa.repos;

import java.util.List;

import com.laputa.dao.User;
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

    @Select("select * from user where id in (select related_user as id from relation where user_id = #{userId})")
    @Results({ @Result(column = "id", property = "id"), @Result(column = "uid", property = "uid"),
            @Result(column = "name", property = "name"), @Result(column = "type", property = "type"),
            @Result(column = "avatar", property = "avatar"), @Result(column = "large_avatar", property = "largeAvatar"),
            @Result(column = "alt", property = "alt") })
    List<User> findFols(int userId);

    @Select("select * from user where id in (select user_id as id from relation where related_user = #{userId})")
    @Results({ @Result(column = "id", property = "id"), @Result(column = "uid", property = "uid"),
            @Result(column = "name", property = "name"), @Result(column = "type", property = "type"),
            @Result(column = "avatar", property = "avatar"), @Result(column = "large_avatar", property = "largeAvatar"),
            @Result(column = "alt", property = "alt") })
    List<User> findFans(int userId);

    @Select("select * from user where id = #{userId}")
    @Results({ @Result(column = "id", property = "id"), @Result(column = "uid", property = "uid"),
            @Result(column = "name", property = "name"), @Result(column = "type", property = "type"),
            @Result(column = "avatar", property = "avatar"), @Result(column = "large_avatar", property = "largeAvatar"),
            @Result(column = "alt", property = "alt") })
    User findById(int userId);

}
