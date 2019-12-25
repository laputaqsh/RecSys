package com.laputa.dto;

import lombok.Data;

/**
 * 用于数据传输
 */

@Data
public class EventDTO {

    private String eventId;

    private String hostName;

    private String eventName;

    private String eventTime;

    private String eventRegion;

    private String eventContent;
}
