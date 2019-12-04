package com.sky.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserForm {

    private String userId;

    private String userPw;

    private String userName;
}
