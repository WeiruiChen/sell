package com.yuhao.sell.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * SmsUtil
 *
 * @author CYH
 * @date 2018/4/12
 */
public class SmsUtil {

    private static final String product = "Dysmsapi";
    private static final String domain = "dysmsapi.aliyuncs.com";

    private static final String accessKeyId = "LTAIntze3be9p9Pd";
    private static final String accessKeySecret = "H2EeSgPc0xsdONf4vlegeoUqxOvsMq";


    public static SendSmsResponse sendSms(String phone,String msg) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("裕豪");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_126340220");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",msg);
        request.setTemplateParam(jsonObject.toJSONString());
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse=null;
        try {
            sendSmsResponse= acsClient.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
        }

        return sendSmsResponse;
    }

    public static void main(String[] args) throws ClientException {

        SmsUtil.sendSms("15084978667","123456");

    }


}
