package com.yuhao.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * WxController
 *
 * @author CYH
 * @date 2018/3/29
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code")String code){
        log.info("进入auth......");
        log.info("code={}",code);

        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx04217e27ba2ec8bc&secret=3367f43020d819c0fb92453f0961a828&code="+code+"&grant_type=authorization_code";

        RestTemplate restTemplate=new RestTemplate();

        String response=restTemplate.getForObject(url,String.class);

        log.info(response);

    }

}
