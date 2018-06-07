package com.yuhao.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * WeChatAccountConfig
 *
 * @author CYH
 * @date 2018/3/29
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    /**
     * 公众号id
     */
    private String mpAppId;

    private String mpAppSecret;

    private String openAppId;

    private String openAppSecret;


    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

    /**
     * 微信模版id
     */
    private Map<String, String> templateId;


}
