package com.yuhao.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * OrderForm
 *
 * @author CYH
 * @date 2018/3/27
 */
@Data
public class OrderForm {


    private String name;


    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "桌号必填")
    private String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;

    @NotEmpty(message = "无此商家")
    private String key;


}
