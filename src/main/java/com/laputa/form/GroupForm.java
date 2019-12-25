package com.laputa.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GroupForm {

    private String groupId;

    private String groupName;
}
