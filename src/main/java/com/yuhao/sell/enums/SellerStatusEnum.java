package com.yuhao.sell.enums;

import com.yuhao.sell.utils.CodeEnum;
import lombok.Getter;

/**
 * SellerStatusEnum
 *
 * @author CYH
 * @date 2018/3/26
 */
@Getter
public enum SellerStatusEnum implements CodeEnum{
    PASS(2, "审核通过"),
    NOT_PASS(0, "待通过"),
    PASS_NO(1, "审核未通过"),
    ;
    private Integer code;

    private String message;

    SellerStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
