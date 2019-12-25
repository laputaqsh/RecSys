package com.laputa.constant;

public interface RedisConstant {

    String PREFIX = CookieConstant.TOKEN.concat("_%s");
    Integer EXPIRE = CookieConstant.EXPIRE;  //2小时
}
