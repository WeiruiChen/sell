package com.yuhao.sell.enums;

import com.yuhao.sell.utils.CodeEnum;
import lombok.Getter;

/**
 * ProductStatusEnum
 *
 * @author CYH
 * @date 2018/3/26
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    /**
     * 在架
     */
    UP(0,"在架"),
    /**
     * 下架
     */
    DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message=message;
    }
}
