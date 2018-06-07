package com.yuhao.sell.service.impl;

import com.yuhao.sell.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * ProductCategoryServiceImplTest
 *
 * @author CYH
 * @date 2018/3/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Test
    public void finOne() throws Exception {
        Optional<ProductCategory> productCategory=productCategoryService.finOne(1);
        Assert.assertEquals(new Integer(1),productCategory.get().getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> productCategoryList=productCategoryService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> productCategoryList=productCategoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        Assert.assertNotEquals(0,productCategoryList.size());

    }

    @Test
    @Transactional
    public void save() throws Exception {

        ProductCategory productCategory=new ProductCategory("男的",2);
        ProductCategory result=productCategoryService.save(productCategory);
        Assert.assertNotEquals(null,result);

    }

}