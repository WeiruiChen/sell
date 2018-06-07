package com.yuhao.sell.utils;


import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * PasswordEncoder
 *
 * @author CYH
 * @date 2018/3/26
 */
@Component
public class PasswordEncoder extends Md5PasswordEncoder {

    @Override
    public String encodePassword(String rawPass, Object salt) {
        return super.encodePassword(rawPass, salt);
    }


    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().length());
    }

}
