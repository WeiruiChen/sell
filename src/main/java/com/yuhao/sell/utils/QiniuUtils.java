package com.yuhao.sell.utils;


import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.yuhao.sell.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import java.util.UUID;
@Slf4j
public class QiniuUtils {



    private static String ak ="-0ZUMHORfo2SicjhTjRheY2jnAlhXBL7hl7LF7uV";

    private static String sk ="ZhB2GMbWKMJfcLPpYkzbFtMWPfzONyHd1MRbQtR0";

    private static String domain ="p4qg9bqm6.bkt.clouddn.com";

    private static String scheme ="http";

    
    private static String bucket = "yuhao";

    public static String upload(byte[] data) {

    	return upload(data, null);
    }
    
    public static String upload(byte[] data, String originalFilename) {
        Auth auth = Auth.create(ak, sk);

        // 简单上传，使用默认策略
        // private String getUpToken0(){
        String upToken = auth.uploadToken(bucket);
        UploadManager uploadManager=null;
        try{
            Configuration cfg = new Configuration(Zone.zone2());//华南区
            cfg.clone();
            uploadManager= new UploadManager(cfg);
        }catch (Error e){
            e.printStackTrace();
        }

        String key = originalFilename;
        if (StringUtils.isBlank(key)) {
        	key = UUID.randomUUID().toString();
        }
        try {
            Response res = uploadManager.put(data, key, upToken);
            
            JSONObject json = JSONObject.parseObject(res.bodyString());
            String fileName = json.getString("key");
            String url = scheme + "://" + domain + "/" + fileName;
            return url;
        } catch (QiniuException e) {
            log.error("上传图片错误",e);
            Response r = e.response;
            try {
                // 响应的文本信息
                log.error(r.bodyString());
            } catch (QiniuException e1) {
                // ignore
            }
        }
        return null;
    }

}
