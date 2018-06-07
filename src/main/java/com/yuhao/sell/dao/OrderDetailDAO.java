package com.yuhao.sell.dao;

import com.yuhao.sell.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * OrderDetailDAO
 *
 * @author CYH
 * @date 2018/3/26
 */
public interface OrderDetailDAO extends JpaRepository<OrderDetail,String>{

    /**
     * 查询详情通过订单id
     * @param orderId
     * @return
     */
    public List<OrderDetail> findByOrderId(String orderId);


}
