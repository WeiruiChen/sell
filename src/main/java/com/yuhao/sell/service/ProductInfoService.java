package com.yuhao.sell.service;

import com.yuhao.sell.dto.CartDTO;
import com.yuhao.sell.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * ProductInfoService
 *
 * @author CYH
 * @date 2018/3/26
 */
public interface ProductInfoService {

    /**
     * 查找商品
     * @param productId
     * @return
     */
    public Optional<ProductInfo> findOne(String productId);

    /**
     * 查找所有上架商品
     * @return
     */
    public List<ProductInfo> findUpAll();

    /**
     * 查找所有上架商品
     * @param sellerId
     * @return
     */
    public List<ProductInfo> findUpAll(String sellerId);


    /**
     * 分页查找
     * @param pageable
     * @return
     */
    public Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 分页查找
     * @param pageable
     * @return
     */
    public Page<ProductInfo> findAll(Pageable pageable,String sellerId);

    /**
     * 增加商品
     * @return
     */
    public ProductInfo save(ProductInfo productInfo);



    /**
     *加库存
     * @param cartDTOList
     */
    public void increaseStock(List<CartDTO> cartDTOList);



    /**
     * 减库存
     * @param cartDTOList
     */
    public void decreaseStock(List<CartDTO> cartDTOList);


    /**
     * 上架
     * @param productId
     * @return
     */
    public ProductInfo onSale(String productId);

    /**
     * 下架
     * @param productId
     * @return
     */
    public ProductInfo offSale(String productId);




}
