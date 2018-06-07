package com.yuhao.sell.service;

import com.yuhao.sell.dto.OrderDTO;

/**
 * BuyerService
 *
 * @author CYH
 * @date 2018/3/27
 */

public interface BuyerService {



    /**
     * 查询一个订单
     * @param openid
     * @param orderId
     * @return
     */
    public OrderDTO findOrderOne(String openid, String orderId);



    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    public OrderDTO cancelOrder(String openid, String orderId);

}
