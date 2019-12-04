package com.sky.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegionForm {

    private String regionId;

    private String regionLongi;

    private String regionLati;
}
