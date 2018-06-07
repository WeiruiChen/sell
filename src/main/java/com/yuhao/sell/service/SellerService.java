package com.yuhao.sell.service;


import com.yuhao.sell.model.SellerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * SellerService
 *
 * @author CYH
 * @date 2018/3/26
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

    /**
     * 查找所有用户
     * @return
     */
    public List<SellerInfo> findAll();

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    public SellerInfo findSellerInfoByUsername(String username);

    /**
     * 通过密码查询用户
     * @param password
     * @return
     */
    public SellerInfo findSellerInfoByPassword(String password);

    /**
     * 查询用户
     * @param password
     * @param username
     * @return
     */
    public SellerInfo findSellerInfoByPasswordAndUsername(String password,String username);


    /**
     * 查询用户
     * @param pageable
     * @return
     */
    public Page<SellerInfo> findList(Pageable pageable);

    /**
     * 通过审核状态查找
     * @param pageable
     * @param audit
     * @return
     */
    public Page<SellerInfo> findList(Pageable pageable,Integer audit);


    /**
     * 查询用户
     * @param id
     * @return
     */
    public SellerInfo findOne(String id);

    /**
     * 通过key查询用户
     * @param key
     * @return
     */
    public SellerInfo findSellerInfoByKey(String key);


    public SellerInfo save(SellerInfo sellerInfo);


    /**
     * 通过手机或者username查重
     * @param username
     * @param phone
     * @return
     */
    public boolean isRepeat(String username,String phone);

    /**
     * 通过手机号查找用户
     * @param phone
     * @return
     */
    public SellerInfo findByPhone(String phone);


    /**
     * 查找使用期已过商户
     * @param trialTime
     * @param pageable
     * @return
     */
    public Page<SellerInfo> findByTrialTimeAfter(Integer audit,Date trialTime,Pageable pageable);

    /**
     * 查找使用日期未过用户
     * @param trialTime
     * @param pageable
     * @return
     */
    public Page<SellerInfo> findByTrialTimeBefore(Integer audit,Date trialTime,Pageable pageable);


}
