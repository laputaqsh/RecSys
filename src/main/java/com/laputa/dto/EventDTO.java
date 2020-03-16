package com.laputa.dto;

import lombok.Data;

/**
 * 用于数据传输
 */

@Data
public class EventDTO {

    private String id;

    private String title;

    private String content;

    private String category;

    private String time;

    private String address;

    private String geo;
}
