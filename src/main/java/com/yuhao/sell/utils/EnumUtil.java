package com.yuhao.sell.utils;

/**
 * EnumUtil
 *
 * @author CYH
 * @date 2018/3/30
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
