package com.yuhao.sell.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * SellerInfo
 *
 * @author CYH
 * @date 2018/3/26
 */
@Data
@Entity
public class SellerInfo {

    @Id
    private String sellerId;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String openid;

    @NotBlank(message = "商户名称不能为空")
    private String merchantName;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "银行卡号不能为空")
    private String idCard;

    private String mail;

    private String businessLicense;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @Column(name = "`key`")
    private String key;

    private Integer audit;

    private Date trialTime;

    private Integer trial;

    private Integer visitCount;




}
