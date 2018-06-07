package com.yuhao.sell.exception;

import com.yuhao.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * SellException
 *
 * @author CYH
 * @date 2018/3/26
 */
@Getter
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
    public SellException(Integer code ,String msg) {
        super(msg);
        this.code=code;
    }

}
