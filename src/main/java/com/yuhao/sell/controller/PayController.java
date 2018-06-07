package com.yuhao.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import com.yuhao.sell.dto.OrderDTO;
import com.yuhao.sell.enums.ResultEnum;
import com.yuhao.sell.exception.SellException;
import com.yuhao.sell.service.OrderMasterService;
import com.yuhao.sell.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * PayController
 *
 * @author CYH
 * @date 2018/3/29
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private PayService payService;


    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){

        OrderDTO orderDTO=orderMasterService.findOne(orderId);
        if (orderDTO==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        PayResponse payResponse=payService.create(orderDTO);


        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("pay/create",map);


    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){

        payService.notify(notifyData);

        //返回处理结果给微信
        return new ModelAndView("pay/success");

    }


}
