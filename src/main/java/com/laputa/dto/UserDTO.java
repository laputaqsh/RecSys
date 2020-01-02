package com.laputa.dto;

import lombok.Data;
import java.util.List;
import com.laputa.dao.User;

/**
 * 用于数据传输
 */

@Data
public class UserDTO {

    private int id;

    private String uid;

    private String name;

    private String type;

    private String avatar;

    private String largeAvatar;

    private String alt;

    private List<Integer> fols;

    private List<Integer> fans;

}
