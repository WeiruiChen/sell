package com.yuhao.sell.service;


import com.yuhao.sell.model.Admin;
import com.yuhao.sell.model.SellerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * AdminService
 *
 * @author CYH
 * @date 2018/3/26
 */
public interface AdminService {

    /**
     * 查询用户
     * @param username
     * @param password
     * @return
     */
    public Admin findByUsernameAndPassword(String username, String password);





}
