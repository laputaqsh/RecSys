package com.sky.dao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sky.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * 地点表
 */
@Entity
@Data
@DynamicUpdate
public class RegionInfo {
    @Id
    private int regionId;

    private String regionLongi;

    private String regionLati;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    public RegionInfo() {
    }

    public RegionInfo(int regionId, String regionLongi, String regionLati) {
        this.regionId = regionId;
        this.regionLongi = regionLongi;
        this.regionLati = regionLati;
    }
}
