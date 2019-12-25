package com.laputa.utils;

import com.laputa.enums.SuperEnum;

public class EnumUtil {

    public static <T extends SuperEnum> String getMsgByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each.getMsg();
            }
        }
        return null;
    }
}
