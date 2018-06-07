package com.yuhao.sell;
import com.jpay.ext.kit.PaymentKit;
import com.jpay.secure.RSAUtils;
import com.jpay.weixin.api.WxPayApi;

import java.util.HashMap;
import java.util.Map;

public class PayApiTest {
    private static final String mch_id="1500080462";//商户号
    private static final String certPath="D:\\qianming\\apiclient_cert.p12";//证书路径
    private static final String partnerKey="6f54166dae87d140e24c6104001a8147";//密钥


	/**
	 * 企业付款到银行卡
	 */
	public void payBank() {
		try {
			//假设获取到的RSA加密公钥为PUBLIC_KEY(PKCS#8格式)
			final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsuA6fen8a59YCDnvSEMa8RCDjpZr0Pqj1NrdVATvcE5W0fq+qiM78D9P58fwtxp35sFigy5srWxxhmme7Q0lbIJLr9KdmC/Pqs4lJappWuvoZI9rKobQxgGSLIREj/2hftGYfRdWWUvtRpg71Nvijv0GZw3ff00fRP9Av/bBSc0CbDBBOePzGV4eDCG13b0a2JxeHBupxNYX2JgpVIFdDfSZRPrv+QozUOj1mA+CeRpN5T8BXzgnzZLv5Ck74d0//WqlHb7uyS2PLbElsen2HxM9wkFxw95dbFd7VLqrrqtH6sFqkUiWL5RhB7HNJG7oZS1OZvqzPJzLT9+bM152EQIDAQAB";
			Map<String, String> params = new HashMap<String, String>();
			params.put("mch_id", mch_id);
			params.put("partner_trade_no", System.currentTimeMillis()+"");
			params.put("nonce_str", System.currentTimeMillis()+"");
			params.put("enc_bank_no", RSAUtils.encryptByPublicKeyByWx("6217907500001304911", PUBLIC_KEY));//收款方银行卡号
			params.put("enc_true_name", RSAUtils.encryptByPublicKeyByWx("陈裕豪", PUBLIC_KEY));//收款方用户名
			params.put("bank_code", "1026");//收款方开户行
			params.put("amount", "1");
			params.put("desc", "微信转账");
			params.put("sign", PaymentKit.createSign(params, partnerKey));
			String payBank = WxPayApi.payBank(params , certPath, mch_id);
            System.out.println(payBank);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PayApiTest pat=new PayApiTest();
		pat.payBank();
	}
}
