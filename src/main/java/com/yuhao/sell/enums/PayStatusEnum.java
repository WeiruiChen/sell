package com.yuhao.sell.enums;

import com.yuhao.sell.utils.CodeEnum;
import lombok.Getter;

/**
 * PayStatusEnum
 *
 * @author CYH
 * @date 2018/3/26
 */
@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),

    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
