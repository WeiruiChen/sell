package com.yuhao.sell.service.impl;


import com.yuhao.sell.dao.AdminDAO;
import com.yuhao.sell.dao.SellerInfoDAO;
import com.yuhao.sell.model.Admin;
import com.yuhao.sell.model.SellerInfo;
import com.yuhao.sell.service.AdminService;
import com.yuhao.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * SellerServiceImpl
 *
 * @author CYH
 * @date 2018/3/26
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        return adminDAO.findByUsernameAndPassword(username,password);
    }
}
