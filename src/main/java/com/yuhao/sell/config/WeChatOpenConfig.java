package com.yuhao.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * WeChatOpenConfig
 *
 * @author CYH
 * @date 2018/3/30
 */
@Component
public class WeChatOpenConfig {

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxOpenService=new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage wxOpenConfigStorage(){

        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage=new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(weChatAccountConfig.getOpenAppId());
        wxMpInMemoryConfigStorage.setSecret(weChatAccountConfig.getOpenAppSecret());

        return wxMpInMemoryConfigStorage;

    }


}
