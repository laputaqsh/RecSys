package com.laputa.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@DynamicUpdate
public class EventUser {

    @Id
    private int id;

    private int eventId;

    private int userId;

}
