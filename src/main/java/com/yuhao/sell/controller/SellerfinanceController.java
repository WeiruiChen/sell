package com.yuhao.sell.controller;

import com.yuhao.sell.config.ProjectUrlConfig;
import com.yuhao.sell.dto.OrderDTO;
import com.yuhao.sell.enums.OrderStatusEnum;
import com.yuhao.sell.enums.ResultEnum;
import com.yuhao.sell.exception.SellException;
import com.yuhao.sell.model.SellerInfo;
import com.yuhao.sell.service.OrderMasterService;
import com.yuhao.sell.service.SellerService;
import com.yuhao.sell.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * SellerfinanceController
 *
 * @author CYH
 * @date 2018/3/30
 */
@Controller
@RequestMapping("/seller/finance")
@Slf4j
public class SellerfinanceController {

    @Autowired
    private OrderMasterService orderService;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @Autowired
    private SecurityUtils  securityUtils;

    @Autowired
    private SellerService sellerService;

    @GetMapping("/day")
    public ModelAndView day(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){

        SellerInfo sellerInfo=securityUtils.getCurrentSeller();
        if (sellerInfo==null){
            map.put("msg","请重新登陆");
            map.put("url", "/sell/seller/onSellerLogin");
            return new ModelAndView("common/error", map);
        }

        PageRequest request=new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage=orderService.findBySellerIdAndOrderStatusAndCreateTimeAfter(sellerInfo.getSellerId(),OrderStatusEnum.FINISHED.getCode(),
                                    new Date(),request);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);

        BigDecimal count=orderService.sum(sellerInfo.getSellerId(),OrderStatusEnum.FINISHED.getCode(),cal.getTime());

        map.put("count",count);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);


        return new ModelAndView("finance/dataCount",map);
    }

    @GetMapping("/week")
    public ModelAndView week(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){

        SellerInfo sellerInfo=securityUtils.getCurrentSeller();
        if (sellerInfo==null){
            map.put("msg","请重新登陆");
            map.put("url", "/sell/seller/onSellerLogin");
            return new ModelAndView("common/error", map);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -7);

        PageRequest request=new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage=orderService.findBySellerIdAndOrderStatusAndCreateTimeAfter(sellerInfo.getSellerId(),OrderStatusEnum.FINISHED.getCode(),
                cal.getTime(),request);

        BigDecimal count=orderService.sum(sellerInfo.getSellerId(),OrderStatusEnum.FINISHED.getCode(),cal.getTime());

        map.put("count",count);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);


        return new ModelAndView("finance/dataCount1",map);
    }

    @GetMapping("/month")
    public ModelAndView month(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){

        SellerInfo sellerInfo=securityUtils.getCurrentSeller();
        if (sellerInfo==null){
            map.put("msg","请重新登陆");
            map.put("url", "/sell/seller/onSellerLogin");
            return new ModelAndView("common/error", map);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);

        PageRequest request=new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage=orderService.findBySellerIdAndOrderStatusAndCreateTimeAfter(sellerInfo.getSellerId(),OrderStatusEnum.FINISHED.getCode(),
                cal.getTime(),request);


        BigDecimal count=orderService.sum(sellerInfo.getSellerId(),OrderStatusEnum.FINISHED.getCode(),cal.getTime());

        map.put("count",count);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);


        return new ModelAndView("finance/dataCount2",map);
    }






}
