package com.laputa.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@DynamicUpdate
public class User {

    @Id
    private int id;

    private String uid;

    private String name;

    private String type;

    private String avatar;

    private String largeAvatar;

    private String alt;

}
