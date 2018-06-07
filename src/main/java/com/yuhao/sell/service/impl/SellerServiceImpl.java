package com.yuhao.sell.service.impl;


import com.yuhao.sell.dao.SellerInfoDAO;
import com.yuhao.sell.model.SellerInfo;
import com.yuhao.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * SellerServiceImpl
 *
 * @author CYH
 * @date 2018/3/26
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoDAO sellerInfoDAO;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoDAO.findByOpenid(openid);
    }

    @Override
    public List<SellerInfo> findAll() {
        return sellerInfoDAO.findAll();
    }

    @Override
    public SellerInfo findSellerInfoByUsername(String username) {
        return sellerInfoDAO.findByUsername(username);
    }

    @Override
    public SellerInfo findSellerInfoByPassword(String password) {
        return sellerInfoDAO.findByPassword(password);
    }

    @Override
    public SellerInfo findSellerInfoByPasswordAndUsername(String password, String username) {
        return sellerInfoDAO.findByUsernameAndPassword(username,password);
    }

    @Override
    public Page<SellerInfo> findList(Pageable pageable) {
        return sellerInfoDAO.findAll(pageable);
    }

    @Override
    public Page<SellerInfo> findList(Pageable pageable, Integer audit) {
        return sellerInfoDAO.findByAudit(audit,pageable);
    }

    @Override
    public SellerInfo findOne(String id) {
        Optional<SellerInfo> sellerInfo=sellerInfoDAO.findById(id);

        if (sellerInfo.isPresent()){
            return sellerInfo.get();
        }
        return null;
    }

    @Override
    public SellerInfo findSellerInfoByKey(String key) {
        return sellerInfoDAO.findByKey(key);
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo) {
        return sellerInfoDAO.save(sellerInfo);

    }

    @Override
    public boolean isRepeat(String username, String phone) {

        if (sellerInfoDAO.findByUsernameOrPhone(username,phone)!=null){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public SellerInfo findByPhone(String phone) {
        return sellerInfoDAO.findByPhone(phone);
    }

    @Override
    public Page<SellerInfo> findByTrialTimeAfter(Integer audit,Date trialTime, Pageable pageable) {
        return sellerInfoDAO.findByAuditAndTrialTimeAfter(audit,trialTime, pageable);
    }

    @Override
    public Page<SellerInfo> findByTrialTimeBefore(Integer audit,Date trialTime, Pageable pageable) {
        return sellerInfoDAO.findByAuditAndTrialTimeBefore(audit,trialTime, pageable);
    }


}
