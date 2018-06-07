package com.yuhao.sell.controller;

import com.yuhao.sell.config.ProjectUrlConfig;
import com.yuhao.sell.dto.OrderDTO;
import com.yuhao.sell.dto.SellerDTO;
import com.yuhao.sell.enums.OrderStatusEnum;
import com.yuhao.sell.enums.SellerStatusEnum;
import com.yuhao.sell.exception.SellException;
import com.yuhao.sell.model.SellerInfo;
import com.yuhao.sell.service.OrderMasterService;
import com.yuhao.sell.service.SellerService;
import com.yuhao.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * AdminController
 *
 * @author CYH
 * @date 2018/4/1
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController{

    @Autowired
    private SellerService sellerService;

    @Autowired
    private OrderMasterService orderService;


    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){

        PageRequest request=new PageRequest(page-1,size);

        Page<SellerInfo> sellerInfoPage=sellerService.findList(request, SellerStatusEnum.NOT_PASS.getCode());
        map.put("sellerInfoPage",sellerInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("day",new Date());

        return new ModelAndView("admin/experience",map);

    }


    @GetMapping("/rlist")
    public ModelAndView rlist(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){

        PageRequest request=new PageRequest(page-1,size);

        Page<SellerInfo> sellerInfoPage=sellerService.findList(request, SellerStatusEnum.PASS.getCode());
        map.put("sellerInfoPage",sellerInfoPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("admin/rlist",map);

    }


    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("id") String id,
                               Map<String, Object> map) {



        SellerInfo sellerInfo = sellerService.findOne(id);

        if(sellerInfo==null){
            log.error("无此用户");
            map.put("msg","无此用户");
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("sellerInfo", sellerInfo);
        return new ModelAndView("admin/detail1", map);
    }

    @GetMapping("/examineOk")
    public ModelAndView examineOk(@RequestParam("sellerId") String sellerId, Map<String, Object> map){

        SellerInfo sellerInfo=sellerService.findOne(sellerId);

        if (sellerInfo.getAudit()==2){
            map.put("msg", "审核状态不正确");
            map.put("url", "/sell/admin/list");
            return new ModelAndView("common/error");
        }

        sellerInfo.setAudit(2);

        if (sellerService.save(sellerInfo)!=null){
            map.put("msg", "审核成功");
            map.put("url", "/sell/admin/list");
            return new ModelAndView("common/success");
        }else {
            map.put("msg", "审核状态修改失败");
            map.put("url", "/sell/admin/list");
            return new ModelAndView("common/error");
        }

    }

    @GetMapping("/examineNot")
    public ModelAndView examineNot(@RequestParam("sellerId") String sellerId, Map<String, Object> map){

        SellerInfo sellerInfo=sellerService.findOne(sellerId);

        if (sellerInfo.getAudit()!=0){
            map.put("msg", "审核状态不正确");
            map.put("url", "/sell/admin/list");
            return new ModelAndView("common/error");
        }
        sellerInfo.setAudit(1);
        if (sellerService.save(sellerInfo)!=null){
            map.put("msg", "审核状态设置成功");
            map.put("url", "/sell/admin/list");
            return new ModelAndView("common/success");
        }else {
            map.put("msg", "审核失败");
            map.put("url", "/sell/admin/list");
            return new ModelAndView("common/error");
        }

    }

    @GetMapping("/turnover")
    public ModelAndView turnover(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                 @RequestParam(value = "size",defaultValue = "10")Integer size,
                                 @RequestParam(value = "date",defaultValue = "1")Integer date,
                                 Map<String,Object> map){


        PageRequest request=new PageRequest(page-1,size);
        Page<SellerInfo> sellerInfoPage=sellerService.findList(request);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -date);

        List<SellerDTO> sellerDTOs=new ArrayList<>();

        for (SellerInfo seller:sellerInfoPage.getContent()) {
            BigDecimal count=orderService.sum(seller.getSellerId(),OrderStatusEnum.FINISHED.getCode(),cal.getTime());
            sellerDTOs.add(new SellerDTO(seller.getName(),count));

        }

        map.put("sellerInfoPage",sellerInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("sellerDTOs",sellerDTOs);
        map.put("date",date);

        return new ModelAndView("admin/dataCount",map);

    }

    @GetMapping("/visit")
    public ModelAndView visit(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                 @RequestParam(value = "size",defaultValue = "10")Integer size,
                                 @RequestParam(value = "date",defaultValue = "1")Integer date,
                                 Map<String,Object> map){


        PageRequest request=new PageRequest(page-1,size);
        Page<SellerInfo> sellerInfoPage=sellerService.findList(request);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -date);


        map.put("sellerInfoPage",sellerInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("date",date);

        return new ModelAndView("admin/click",map);

    }


    @GetMapping("/udate")
    public ModelAndView udate(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){

        PageRequest request=new PageRequest(page-1,size);
        Page<SellerInfo> sellerInfoPage=sellerService.findByTrialTimeBefore(SellerStatusEnum.PASS.getCode(),new Date(),request);
        map.put("sellerInfoPage",sellerInfoPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("admin/udate",map);

    }

    @GetMapping("/rdate")
    public ModelAndView rdate(@RequestParam(value = "page",defaultValue = "1")Integer page,
                              @RequestParam(value = "size",defaultValue = "10")Integer size,
                              Map<String,Object> map){

        PageRequest request=new PageRequest(page-1,size);

        Page<SellerInfo> sellerInfoPage=sellerService.findByTrialTimeAfter(SellerStatusEnum.PASS.getCode(),new Date(),request);
        map.put("sellerInfoPage",sellerInfoPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("admin/rdate",map);

    }


    @PostMapping("/trial")
    public ModelAndView trial(@RequestParam("sellerId")String sellerId,@RequestParam("date")Integer date,
                       Map<String,Object> map){


        SellerInfo sellerInfo=sellerService.findOne(sellerId);

        sellerInfo.setTrial(date);

        Date day=new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, date);

        sellerInfo.setTrialTime(calendar.getTime());


        sellerService.save(sellerInfo);

        map.put("msg", "操作成功!");
        map.put("url", "/sell/admin/list");

        return new ModelAndView("common/success");

    }

    @PostMapping("/strial")
    public ModelAndView strial(@RequestParam("sellerId")String sellerId, Map<String,Object> map){


        SellerInfo sellerInfo=sellerService.findOne(sellerId);

        sellerInfo.setTrial(0);

        sellerInfo.setTrialTime(null);

        sellerService.save(sellerInfo);

        map.put("msg", "操作成功!");
        map.put("url", "/sell/admin/list");

        return new ModelAndView("common/success");

    }

    @PostMapping("/use")
    public ModelAndView use(@RequestParam("sellerId")String sellerId,@RequestParam("date")Integer date,
                              Map<String,Object> map){

        SellerInfo sellerInfo=sellerService.findOne(sellerId);

        sellerInfo.setTrial(date);

        Date day=new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, date);

        sellerInfo.setTrialTime(calendar.getTime());

        sellerInfo.setAudit(SellerStatusEnum.PASS.getCode());

        sellerService.save(sellerInfo);

        map.put("msg", "操作成功!");
        map.put("url", "/sell/admin/udate");

        return new ModelAndView("common/success");

    }

    @PostMapping("/suse")
    public ModelAndView suse(@RequestParam("sellerId")String sellerId,
                            Map<String,Object> map){

        SellerInfo sellerInfo=sellerService.findOne(sellerId);

        sellerInfo.setTrial(0);
        Date day=new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, -1);

        sellerInfo.setTrialTime(calendar.getTime());

        sellerService.save(sellerInfo);

        map.put("msg", "操作成功!");
        map.put("url", "/sell/admin/rdate");

        return new ModelAndView("common/success");

    }











}
