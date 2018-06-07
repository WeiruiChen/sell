package com.yuhao.sell.dao;

import com.yuhao.sell.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * OrderMasterDAO
 *
 * @author CYH
 * @date 2018/3/26
 */
public interface OrderMasterDAO extends JpaRepository<OrderMaster,String >{

    /**
     * 查询订单by openid
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    public Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    /**
     * 查询订单
     * @param buyerOpenid
     * @param sellerId
     * @param pageable
     * @return
     */
    public Page<OrderMaster> findByBuyerOpenidAndSellerId(String buyerOpenid, String sellerId,Pageable pageable);


    /**
     * 查询订单by sellerId
     * @param sellerId
     * @param pageable
     * @return
     */
    public Page<OrderMaster> findBySellerId(String sellerId,Pageable pageable);

    /**
     * 通过状态查询
     * @param sellerId
     * @param orderStatus
     * @param pageable
     * @return
     */
    public Page<OrderMaster> findBySellerIdAndOrderStatus(String sellerId,Integer orderStatus,Pageable pageable);

    /**
     * 通过创建时间和订单状态查找订单
     * @param sellerId
     * @param orderStatus
     * @param createTime
     * @param pageable
     * @return
     */
    public Page<OrderMaster> findBySellerIdAndOrderStatusAndCreateTimeAfter(String sellerId, Integer orderStatus, Date createTime,Pageable pageable);

    /**
     * 统计订单
     * @param sellerId
     * @param orderStatus
     * @param createTime
     * @return
     */
    public List<OrderMaster> findBySellerIdAndOrderStatusAndCreateTimeAfter(String sellerId, Integer orderStatus, Date createTime);


    /**
     * 查找需要结账的订单
     * @param sellerId
     * @param orderStatus
     * @param payStatus
     * @param isEnd
     * @return
     */
    public List<OrderMaster> findBySellerIdAndOrderStatusAndPayStatusAndIsEnd(String sellerId,Integer orderStatus,Integer payStatus,Integer isEnd);


}
