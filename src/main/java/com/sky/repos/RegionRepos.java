package com.sky.repos;

import com.sky.dao.RegionInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepos {

    @Select("select * from region_info where region_id = #{regionId}")
    @Results({
            @Result(column = "region_id", property = "regionId"),
            @Result(column = "region_longi", property = "regionLongi"),
            @Result(column = "region_lati", property = "regionLati"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    RegionInfo findById(int regionId);

    @Insert("insert into region_info(region_id, region_longi, region_lati) values(#{regionId}, #{regionLongi}, #{regionLati})")
    void insert(int regionId, String regionLongi, String regionLati);
}