package com.yuhao.sell.dao;


import com.yuhao.sell.model.SellerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * SellerInfoDAO
 *
 * @author CYH
 * @date 2018/3/26
 */
public interface SellerInfoDAO extends JpaRepository<SellerInfo, String> {

    /**
     * findByOpenid
     * @param openid
     * @return SellerInfo
     */
    public SellerInfo findByOpenid(String openid);

    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    public SellerInfo findByUsername(String username);

    /**
     * 通过密码查询用户
     * @param password
     * @return
     */
    public SellerInfo findByPassword(String password);

    /**
     * 查询用户
     * @param password
     * @param username
     * @return
     */

    public SellerInfo findByUsernameAndPassword(String username,String password);


    /**
     * 通过私钥查询用户
     * @param key
     * @return
     */
    public SellerInfo findByKey(String key);

    /**
     * 通过审核状态查找
     * @param audit
     * @param pageable
     * @return
     */
    public Page<SellerInfo> findByAudit(Integer audit, Pageable pageable);

    /**
     * 通过username或者phone查找用户
     * @param username
     * @param phone
     * @return
     */
    public SellerInfo findByUsernameOrPhone(String username,String phone);


    /**
     * 通过手机号查找用户
     * @param phone
     * @return
     */
    public SellerInfo findByPhone(String phone);

    /**
     * 查找使用期已过商户
     * @param audit
     * @param trialTime
     * @param pageable
     * @return
     */
    public Page<SellerInfo> findByAuditAndTrialTimeAfter(Integer audit,Date trialTime,Pageable pageable);

    /**
     * 查找使用日期未过用户
     * @param audit
     * @param trialTime
     * @param pageable
     * @return
     */
    public Page<SellerInfo> findByAuditAndTrialTimeBefore(Integer audit,Date trialTime,Pageable pageable);







}
