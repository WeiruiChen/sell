package com.yuhao.sell.aspect;


import com.yuhao.sell.constant.CookieConstant;
import com.yuhao.sell.constant.RedisConstant;
import com.yuhao.sell.exception.AdminAuthorizeException;
import com.yuhao.sell.exception.SellerAuthorizeException;
import com.yuhao.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * SellerAuthorizeAspect
 *
 * @author CYH
 * @date 2018/3/29
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.yuhao.sell.controller.Seller*.*(..))" +
    "&& !execution(public * com.yuhao.sell.controller.SellerUserController.*(..))")
    public void verify() {}

    @Pointcut("execution(public * com.yuhao.sell.controller.AdminController.*(..))")
    public void admin(){}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthorizeException();
        }
        System.out.println("cookie:"+cookie.getValue());
        System.out.println("tokenValue:"+tokenValue);

    }

    @Before("admin()")
    public void doAdmin(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.ADMIN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            throw new AdminAuthorizeException();
        }

        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            throw new AdminAuthorizeException();
        }
        System.out.println("cookie:"+cookie.getValue());
        System.out.println("tokenValue:"+tokenValue);
    }




}