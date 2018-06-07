package com.yuhao.sell.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * CardUtil
 *
 * @author CYH
 * @date 2018/4/22
 */

public class CardUtil {

    public  Map<String,String> bankName = new HashMap<>();

    private static CardUtil instance=new CardUtil();


    private CardUtil(){
        bankName.put("ICBC","1002");
        bankName.put("BOC","1026");
        bankName.put("CCB","1003");
        bankName.put("ABC","1005");
        bankName.put("PSBC","1066");
        bankName.put("CEB","1022");
        bankName.put("BCM","1020");
        bankName.put("CMB","1001");
        bankName.put("CMBC","1006");
        bankName.put("CIB","1009");
        bankName.put("SPDB","1004");
        bankName.put("CEB","1010");
        bankName.put("CNCB","1021");
        bankName.put("HXB","1025");
        bankName.put("CGB","1027");
        bankName.put("BOB","1032");
        bankName.put("NBB","1056");
    }

    public static CardUtil getInstance(){
        return instance;
    }


    public  String getBank(String bankStr){

       return bankName.get(bankStr);
    }


    public   String api="https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=%s&cardBinCheck=true";

    public  String getBankCode(String bank){

        String http=String.format(api, bank);

        JSONObject jsonObject=HttpRequestUtil.httpGet(http);

        if (jsonObject==null){
            return null;
        }
        if (!jsonObject.getBoolean("validated")){
            return null;
        }

        String bankCode=CardUtil.getInstance().getBank(jsonObject.getString("bank"));

        return bankCode;
    }





}

