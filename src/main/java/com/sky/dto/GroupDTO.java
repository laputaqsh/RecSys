package com.sky.dto;

import lombok.Data;

/**
 * 用于数据传输
 */

@Data
public class GroupDTO {

    private String groupId;

    private String hostName;

    private String groupName;

    private String groupDetail;

    private String groupRegion;
}
