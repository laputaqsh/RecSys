package com.laputa.enums;

import lombok.Getter;

@Getter
public enum TypeEnum {
    OWNER(11, "owner"),
    WISHER(12, "wisher"),
    PARTICIPANT(13, "participant");

    private Integer code;

    private String msg;

    TypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
