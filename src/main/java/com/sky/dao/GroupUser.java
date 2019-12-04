package com.sky.dao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sky.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * 活动-用户表
 */
@Entity
@Data
@DynamicUpdate
public class GroupUser {
    @Id
    private int id;

    private int groupId;

    private int userId;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    public GroupUser() {
    }

    public GroupUser(int groupId, int userId) {
        this.groupId = groupId;
        this.userId = userId;
    }
}
