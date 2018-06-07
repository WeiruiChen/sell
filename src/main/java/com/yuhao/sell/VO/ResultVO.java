package com.yuhao.sell.VO;

import lombok.Data;

/**
 * 返回给前端最外层对象
 *
 * @author CYH
 * @date 2018/3/26
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 对象
     */
    private T data;

}
