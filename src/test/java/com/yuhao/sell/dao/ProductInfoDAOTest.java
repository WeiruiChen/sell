package com.yuhao.sell.dao;

import com.yuhao.sell.model.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * ProductInfoDAOTest
 *
 * @author CYH
 * @date 2018/3/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDAOTest {

    @Autowired
    private ProductInfoDAO productInfoDAO;


    @Test
    public void saveTest(){

        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("宫爆鸡丁");
        productInfo.setProductPrice(new BigDecimal(8.5));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("http://xxxxx.png");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        ProductInfo result=productInfoDAO.save(productInfo);
        Assert.assertNotEquals(null,result);


    }


    @Test
    public void findByProductStatus() throws Exception {

        List<ProductInfo> productInfoList=productInfoDAO.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());

    }

}