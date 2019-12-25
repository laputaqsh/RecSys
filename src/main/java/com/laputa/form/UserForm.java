package com.laputa.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserForm {

    private int userId;

    private String userPw;

    private String userName;
}
