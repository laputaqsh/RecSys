package com.laputa.dto;

import lombok.Data;

/**
 * 用于数据传输
 */

@Data
public class UserDTO {

    private String id;

    private String uid;

    private String name;

    private String type;

    private String avatar;

    private String largeAvatar;

    private String alt;

    private int fols;

    private int fans;

}
