package com.laputa.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class Event {

    @Id
    private int id;

    private int ownerId;

    private String title;

    private String content;

    private String category;

    private String categoryName;

    private Date beginTime;

    private Date endTime;

    private String image;

    private int locId;

    private String locName;

    private int wisherCount;

    private String hasTicket;

    private String canInvite;

    private String timeStr;

    private int album;

    private int participantCount;

    private String tags;

    private String imageHlarge;

    private String priceRange;

    private String geo;

    private String imageLmobile;

    private String address;

}
