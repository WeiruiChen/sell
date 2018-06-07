package com.yuhao.sell.service;


import com.yuhao.sell.dto.OrderDTO;

/**
 * PushMessageService
 *
 * @author CYH
 * @date 2018/3/29
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    public void orderStatus(OrderDTO orderDTO);
}
