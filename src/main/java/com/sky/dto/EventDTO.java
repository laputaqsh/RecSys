package com.sky.dto;

import lombok.Data;

import java.sql.Date;

/**
 * 用于数据传输
 */

@Data
public class EventDTO {

    private int eventId;

    private String hostName;

    private String eventName;

    private String eventTime;

    private String eventRegion;

    private String eventContent;
}
