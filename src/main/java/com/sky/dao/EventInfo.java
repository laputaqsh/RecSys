package com.sky.dao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sky.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * 活动表
 */
@Entity
@Data
@DynamicUpdate
public class EventInfo {
    @Id
    private int eventId;

    private int regionId;

    private int eventTime;

    private String eventName;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    public EventInfo() {
    }

    public EventInfo(int eventId, int regionId, int eventTime, String eventName) {
        this.eventId = eventId;
        this.regionId = regionId;
        this.eventTime = eventTime;
        this.eventName = eventName;
    }
}
