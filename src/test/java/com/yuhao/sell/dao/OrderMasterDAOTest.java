package com.yuhao.sell.dao;

import com.yuhao.sell.enums.OrderStatusEnum;
import com.yuhao.sell.model.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * OrderMasterDAOTest
 *
 * @author CYH
 * @date 2018/3/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDAOTest {


    @Autowired
    private OrderMasterDAO repository;

    private final String OPENID = "11011110";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("12345678");
        orderMaster.setBuyerName("CYH");
        orderMaster.setBuyerPhone("15084978667");
        orderMaster.setBuyerAddress("湖南");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(12.5));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(1, 3);

        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);

        Assert.assertNotEquals(0, result.getTotalElements());
    }

    @Test
    public void sumTest(){

        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);

        List<OrderMaster> orderMasters=repository.findBySellerIdAndOrderStatusAndCreateTimeAfter("abc", OrderStatusEnum.FINISHED.getCode(),cal.getTime());

        System.out.println(orderMasters.size());

    }

}