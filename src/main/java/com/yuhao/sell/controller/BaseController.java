package com.yuhao.sell.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.yuhao.sell.config.ProjectUrlConfig;
import com.yuhao.sell.constant.CookieConstant;
import com.yuhao.sell.constant.RedisConstant;
import com.yuhao.sell.enums.ResultEnum;
import com.yuhao.sell.enums.SellerStatusEnum;
import com.yuhao.sell.model.Admin;
import com.yuhao.sell.model.SellerInfo;
import com.yuhao.sell.service.AdminService;
import com.yuhao.sell.service.SellerService;
import com.yuhao.sell.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * BaseController
 *
 * @author CYH
 * @date 2018/4/2
 */
@Controller
@RequestMapping("/admin")
public class BaseController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SellerService sellerService;


    @GetMapping("/onAdminLogin")
    public ModelAndView onLogin(){

        return new ModelAndView("admin/login_1");
    }

    @GetMapping("/modifyPwd")
    public ModelAndView modifyPwd(){
        return new ModelAndView("admin/modifyPwd");
    }

    @PostMapping("/onPwd")
    public ModelAndView onPwd(@RequestParam("phone")String phone,
                              @RequestParam("password")String password,
                              @RequestParam("identifying")String identifying,
                              Map<String, Object> map){

        if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)||StringUtils.isEmpty(identifying)){
            map.put("msg", "信息为空，请填写再试!");
            map.put("url", "/sell/admin/modifyPwd");
            return new ModelAndView("common/error");
        }

        SellerInfo sellerInfo=sellerService.findByPhone(phone);
        if (sellerInfo==null){
            map.put("msg", "手机号填写错误请重试");
            map.put("url", "/sell/admin/modifyPwd");
            return new ModelAndView("common/error");
        }

        String tokenValue = redisTemplate.opsForValue().get(phone);
        if (tokenValue!=null&&tokenValue.equals(identifying)){

            redisTemplate.opsForValue().getOperations().delete(phone);

            sellerInfo.setPassword(passwordEncoder.encodePassword(password,sellerInfo.getUsername()));

            sellerService.save(sellerInfo);

            map.put("msg", "修改密码成功!");
            map.put("url", "/sell/seller/onSellerLogin");
            return new ModelAndView("common/success", map);

        }else {
            map.put("msg", "验证码错误，请重试!");
            map.put("url", "/sell/admin/modifyPwd");
            return new ModelAndView("common/error");
        }


    }


    @ResponseBody
    @PostMapping("/identifying")
    public void identifying(@RequestParam("phone")String phone){

        String identifying=((int)((Math.random()*9+1)*100000))+"";

        SendSmsResponse sendSmsResponse=null;

        try {
            sendSmsResponse=SmsUtil.sendSms(phone,identifying);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse!=null&&"OK".equals(sendSmsResponse.getCode())){
            Integer expire=RedisConstant.ID_EXPIRE;
            redisTemplate.opsForValue().set(phone, identifying, expire, TimeUnit.SECONDS);
        }

    }


    /**
     *用户名密码登陆
     * @return
     */
    @PostMapping("/AdminLogin")
    public ModelAndView adminLogin(@RequestParam("username")String username,
                                   @RequestParam("password")String password,
                                   HttpServletResponse response,
                                   Map<String, Object> map){

        if (StringUtils.isEmpty(username)){
            map.put("msg", ResultEnum.NULL_USERNAME.getMessage());
            map.put("url", "/sell/admin/onAdminLogin");
            return new ModelAndView("common/error");
        }
        if (StringUtils.isEmpty(password)){
            map.put("msg", ResultEnum.NULL_PASSWORD.getMessage());
            map.put("url", "/sell/admin/onAdminLogin");
            return new ModelAndView("common/error");
        }


        Admin admin=adminService.findByUsernameAndPassword(username,passwordEncoder.encodePassword(password,username));

        if (admin==null){
            map.put("msg", ResultEnum.USERNAME_PASSWORD_ERROR.getMessage());
            map.put("url", "/sell/admin/onAdminLogin");
            return new ModelAndView("common/error");
        }

        //2. 设置token至redis
        //String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, passwordEncoder.encodePassword("33",admin.getUsername())), passwordEncoder.encodePassword("33",admin.getUsername()), expire, TimeUnit.SECONDS);

        //3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.ADMIN, passwordEncoder.encodePassword("33",admin.getUsername()), expire);


        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/admin/list");

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.ADMIN);
        if (cookie != null) {
            //2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.ADMIN, null, 0);

        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/sell/admin/onAdminLogin");
        return new ModelAndView("common/success", map);
    }

    @PostMapping("/sellerRegister")
    public ModelAndView sellerRegister(@Valid SellerInfo sellerInfo,
                                       BindingResult bindingResult,
                                       Map<String,Object> map){
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/onSellerRegister");
            return new ModelAndView("common/error", map);
        }

        if (sellerService.isRepeat(sellerInfo.getUsername(),sellerInfo.getPhone())){
            map.put("msg", "手机号或者用户名重复");
            map.put("url", "/sell/seller/onSellerRegister");
            return new ModelAndView("common/error", map);
        }

        if (CardUtil.getInstance().getBankCode(sellerInfo.getIdCard())==null){
            map.put("msg", "银行卡类型错误");
            map.put("url", "/sell/seller/onSellerRegister");
            return new ModelAndView("common/error", map);
        }


        try {

            sellerInfo.setSellerId(KeyUtil.genUniqueKey());
            sellerInfo.setKey(UUID.randomUUID().toString());
            sellerInfo.setAudit(SellerStatusEnum.NOT_PASS.getCode());
            sellerInfo.setPassword(passwordEncoder.encodePassword(sellerInfo.getPassword(),sellerInfo.getUsername()));
            sellerService.save(sellerInfo);

        }catch (Exception e){
            e.printStackTrace();
            map.put("msg", "注册失败请重试");
            map.put("url", "/sell/seller/onSellerRegister");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "注册成功");
        map.put("url", "/sell/seller/onSellerLogin");

        return new ModelAndView("common/success", map);
    }



}
