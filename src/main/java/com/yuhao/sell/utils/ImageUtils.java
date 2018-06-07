package com.yuhao.sell.utils;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    public static String getSmallFileName(String path, int width) {
        String suffix = path.substring(path.lastIndexOf("."));
        path = path.substring(0, path.lastIndexOf(".")) + "_" + width + suffix;
        return path;
    }
    
    /**
     * 上传图片到cdn并返回完整访问路径
     * @return
     */
    public static String uploadToCDNAndGetUrl(byte[] data) {
        return QiniuUtils.upload(data);
    }
}
