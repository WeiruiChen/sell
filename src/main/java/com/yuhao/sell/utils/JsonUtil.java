package com.yuhao.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * JsonUtil
 *
 * @author CYH
 * @date 2018/3/29
 */
public class JsonUtil {

    public static String toJson(Object object){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();

        return gson.toJson(object);

    }

}
