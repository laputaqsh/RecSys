package com.laputa.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于数据传输
 */

@Data
public class UserDTO {

    private String userId;

    private String userPw;

    private String userName;

    private String userContent;

    private String userRegionId;

    private List<EventDTO> userPastEventList = new ArrayList<>();

}
