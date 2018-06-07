package com.yuhao.sell.enums;

import com.yuhao.sell.utils.CodeEnum;
import lombok.Getter;

/**
 * OrderStatusEnum
 *
 * @author CYH
 * @date 2018/3/26
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
