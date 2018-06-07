package com.yuhao.sell.service.impl;

import com.yuhao.sell.dao.ProductCategoryDAO;
import com.yuhao.sell.model.ProductCategory;
import com.yuhao.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ProductCategoryServiceImpl
 *
 * @author CYH
 * @date 2018/3/26
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{


    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Override
    public Optional<ProductCategory> finOne(Integer id) {
        return productCategoryDAO.findById(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDAO.findAll();
    }

    @Override
    public List<ProductCategory> findAll(String sellerId) {
        return productCategoryDAO.findBySellerId(sellerId);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDAO.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDAO.save(productCategory);
    }
}
