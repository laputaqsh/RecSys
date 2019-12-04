package com.sky.dao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sky.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * 用户表
 */
@Entity
@Data
@DynamicUpdate
public class UserInfo {
    @Id
    private int userId;

    private String userPw;

    private String userName;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    public UserInfo() {
    }

    public UserInfo(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
