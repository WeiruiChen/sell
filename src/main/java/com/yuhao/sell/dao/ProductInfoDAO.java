package com.yuhao.sell.dao;

import com.yuhao.sell.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductInfoDAO
 *
 * @author CYH
 * @date 2018/3/26
 */
public interface ProductInfoDAO extends JpaRepository<ProductInfo,String>{

    /**
     *通过商品状态查找
     * @param productStatus
     * @return
     */
    public List<ProductInfo> findByProductStatus(Integer productStatus);

    /**
     * 通过商品状态查找
     * @param productStatus
     * @param sellerId
     * @return
     */
    public List<ProductInfo> findByProductStatusAndSellerId(Integer productStatus,String sellerId);

    /**
     * 查找
     * @param sellerId
     * @param pageable
     * @return
     */
    public Page<ProductInfo> findBySellerId(String sellerId,Pageable pageable);

}
