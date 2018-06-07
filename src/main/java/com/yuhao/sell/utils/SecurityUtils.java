package com.yuhao.sell.utils;

import com.yuhao.sell.constant.CookieConstant;
import com.yuhao.sell.constant.RedisConstant;
import com.yuhao.sell.exception.SellerAuthorizeException;
import com.yuhao.sell.model.SellerInfo;
import com.yuhao.sell.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * SecurityUtils
 *
 * @author CYH
 * @date 2018/4/2
 */
@Component
@Slf4j
public class SecurityUtils {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SellerService sellerService;

    public SellerInfo getCurrentSeller(){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            return null;
        }

        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            return null;
        }
        return sellerService.findSellerInfoByKey(tokenValue);

    }




}
