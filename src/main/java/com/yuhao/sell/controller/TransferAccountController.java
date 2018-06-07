package com.yuhao.sell.controller;

import com.jpay.ext.kit.PaymentKit;
import com.jpay.secure.RSAUtils;
import com.jpay.weixin.api.WxPayApi;
import com.yuhao.sell.config.WeChatAccountConfig;
import com.yuhao.sell.enums.OrderStatusEnum;
import com.yuhao.sell.enums.PayStatusEnum;
import com.yuhao.sell.model.OrderMaster;
import com.yuhao.sell.model.SellerInfo;
import com.yuhao.sell.service.OrderMasterService;
import com.yuhao.sell.service.SellerService;
import com.yuhao.sell.utils.CardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TransferAccountController
 *
 * @author CYH
 * @date 2018/4/21
 */
@Controller
@Slf4j
public class TransferAccountController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;


    /**
     * 企业付款到银行卡
     */
    public String payBank(String name,String account,String amount,String bankCode) throws Exception {

            //假设获取到的RSA加密公钥为PUBLIC_KEY(PKCS#8格式)
            final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsuA6fen8a59YCDnvSEMa8RCDjpZr0Pqj1NrdVATvcE5W0fq+qiM78D9P58fwtxp35sFigy5srWxxhmme7Q0lbIJLr9KdmC/Pqs4lJappWuvoZI9rKobQxgGSLIREj/2hftGYfRdWWUvtRpg71Nvijv0GZw3ff00fRP9Av/bBSc0CbDBBOePzGV4eDCG13b0a2JxeHBupxNYX2JgpVIFdDfSZRPrv+QozUOj1mA+CeRpN5T8BXzgnzZLv5Ck74d0//WqlHb7uyS2PLbElsen2HxM9wkFxw95dbFd7VLqrrqtH6sFqkUiWL5RhB7HNJG7oZS1OZvqzPJzLT9+bM152EQIDAQAB";
            Map<String, String> params = new HashMap<String, String>();
            params.put("mch_id", weChatAccountConfig.getMchId());
            params.put("partner_trade_no", System.currentTimeMillis()+"");
            params.put("nonce_str", System.currentTimeMillis()+"");
            /**
             * 收款方银行卡号
             */
            params.put("enc_bank_no", RSAUtils.encryptByPublicKeyByWx(account, PUBLIC_KEY));
            /**
             * 收款方用户名
             */
            params.put("enc_true_name", RSAUtils.encryptByPublicKeyByWx(name, PUBLIC_KEY));
            /**
             * 收款方开户行
             */
            params.put("bank_code", bankCode);
            params.put("amount", amount);
            params.put("desc", "微信转账");
            params.put("sign", PaymentKit.createSign(params, weChatAccountConfig.getMchKey()));
            String payBank = WxPayApi.payBank(params , weChatAccountConfig.getKeyPath(), weChatAccountConfig.getMchId());
            return payBank;


    }



    @Scheduled(cron = "0 0 23 * * ?")
    public void transferAccount() {

        List<SellerInfo> sellerInfos=sellerService.findAll();

        for (SellerInfo sellerInfo:sellerInfos){

            List<OrderMaster> orderMasters=orderMasterService.findBySellerIdAndOrderStatusAndPayStatusAndIsEnd(sellerInfo.getSellerId(),
                    OrderStatusEnum.FINISHED.getCode(), PayStatusEnum.SUCCESS.getCode(),0);
            BigDecimal sum=new BigDecimal(0);
            for (OrderMaster orderMaster:orderMasters) {
                sum.add(orderMaster.getOrderAmount());
            }
            //1.进行转账

            String bankCode= CardUtil.getInstance().getBankCode(sellerInfo.getIdCard());
            if (bankCode==null){
                log.error("银行卡类型出错:",sellerInfo.getName(),sellerInfo.getIdCard());
                continue;
            }
            if (sum.intValue()<1){
                log.error("今日金额少于1元:",sum.floatValue(),sellerInfo.getName(),sellerInfo.getIdCard());
                continue;
            }

            String response="";
            try {
                 response=payBank(sellerInfo.getName(),sellerInfo.getIdCard(),sum.floatValue()+"",bankCode);
            } catch (Exception e) {
                log.error("支付出错:",sellerInfo.getName(),sellerInfo.getIdCard());
                e.printStackTrace();
                continue;
            }
            //2.判断是否支付成功

            if (response.contains("支付成功")){
                //3.更新订单状态
                for (OrderMaster orderMaster:orderMasters) {
                    orderMaster.setIsEnd(1);
                    orderMasterService.endOrder(orderMaster);
                }
                log.error("支付成功:",sellerInfo.getName(),sellerInfo.getIdCard());
            }else {
                log.error("支付失败:",sellerInfo.getName(),sellerInfo.getIdCard());
            }

            log.error("支付信息:",response);

        }

    }


}
