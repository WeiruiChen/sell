package com.yuhao.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.yuhao.sell.dto.OrderDTO;

/**
 * PayService
 *
 * @author CYH
 * @date 2018/3/29
 */
public interface PayService {

    /**
     * 创建订单
     * @param orderDTO
     * @return PayResponse
     */
    public PayResponse create(OrderDTO orderDTO);

    /**
     * 支付完成通知
     * @param notifyData
     * @return PayResponse
     */
    public PayResponse notify(String notifyData);


    /**
     * 退款
     * @param orderDTO
     * @return RefundResponse
     */
    public RefundResponse refund(OrderDTO orderDTO);

}
