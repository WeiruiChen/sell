package com.yuhao.sell.service;

import com.yuhao.sell.model.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * ProductCategoryService
 *
 * @author CYH
 * @date 2018/3/26
 */

public interface ProductCategoryService {

    /**
     * 查找一个
     * @param id
     * @return
     */
    public Optional<ProductCategory> finOne(Integer id);

    /**
     * 所有
     * @return
     */
    public List<ProductCategory> findAll();

    /**
     * 所有by sellerId
     * @param sellerId
     * @return
     */
    public List<ProductCategory> findAll(String sellerId);

    /**
     * 类目查询
     * @param categoryTypeList
     * @return
     */
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 增加更新
     * @param productCategory
     * @return
     */
    public ProductCategory save(ProductCategory productCategory);


}
