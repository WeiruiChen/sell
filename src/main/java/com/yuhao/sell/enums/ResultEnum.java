package com.yuhao.sell.enums;

import lombok.Getter;

/**
 * ResultEnum
 *
 * @author CYH
 * @date 2018/3/26
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"支付状态不正确"),
    CART_EMPTY(18,"购物车不能为空"),
    PARAM_ERROR(19,"订单参数不正确"),
    ORDER_OWNER_ERROR(20,"订单的用户不正确"),
    WECHAT_MP_ERROR(21,"微信公众账号错误"),
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(22,"支付金额错误"),
    ORDER_CANCEL_SUCCESS(23,"取消订单成功"),
    ORDER_FINISH_SUCCESS(24,"完结订单成功"),
    PRODUCT_STATUS_ERROR(25,"商品状态错误"),
    LOGOUT_SUCCESS(26,"退出登录成功"),
    LOGIN_FAIL(27,"登录失败"),
    NULL_USERNAME(28,"用户名为空"),
    NULL_PASSWORD(29,"密码为空"),
    USERNAME_PASSWORD_ERROR(30,"用户名或者密码错误"),
    NULL_MERCHANT(31,"商户不存在")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
