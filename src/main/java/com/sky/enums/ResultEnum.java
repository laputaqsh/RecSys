package com.sky.enums;

import lombok.Getter;

@Getter
public enum ResultEnum implements SuperEnum {
    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数不正确"),
    LOGIN_SUCCESS(11, "登录成功"),
    LOGIN_FAIL(12, "登录失败"),
    LOGOUT_SUCCESS(21, "登出成功"),
    LOGOUT_FAIL(22, "登出失败");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
