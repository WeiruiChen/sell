package com.yuhao.sell.dao;

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

import static org.junit.Assert.*;

/**
 * ProductCategoryDAOTest
 *
 * @author CYH
 * @date 2018/3/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDAOTest {

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Test
    public void findOneTest(){
        System.out.println(productCategoryDAO.findById(1).toString());

    }
    @Test
    @Transactional
    public void saveTest(){

        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("你最爱");
        productCategory.setCategoryType(3);

        productCategoryDAO.save(productCategory);


    }

    @Test
    public void findByCategoryTypeInTest(){

        List<Integer> list= Arrays.asList(1,2,3);

        List<ProductCategory> productCategoryList=productCategoryDAO.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0,productCategoryList.size());


    }


}