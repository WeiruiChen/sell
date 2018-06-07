package com.yuhao.sell.service.impl;

import com.yuhao.sell.enums.ProductStatusEnum;
import com.yuhao.sell.model.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * ProductInfoServiceImplTest
 *
 * @author CYH
 * @date 2018/3/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void finOne() throws Exception {
        Optional<ProductInfo> productInfo=productInfoService.findOne("123456");
        Assert.assertEquals("123456",productInfo.get().getProductId());

    }

    @Test
    public void findUpAll() throws Exception {

        List<ProductInfo> productInfoList=productInfoService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());

    }

    @Test
    public void findAll() throws Exception {

        PageRequest request=new PageRequest(0,2);

        Page<ProductInfo> productInfoPage=productInfoService.findAll(request);
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());

    }

    @Test
    public void save() throws Exception {

        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("宫保鸡丁");
        productInfo.setProductPrice(new BigDecimal(10.5));
        productInfo.setProductStock(200);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("http://xxxxx.png");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result=productInfoService.save(productInfo);

        Assert.assertNotEquals(null,result);


    }

}