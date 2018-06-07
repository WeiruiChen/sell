package com.yuhao.sell.dao;

import com.yuhao.sell.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * AdminDAO
 *
 * @author CYH
 * @date 2018/4/2
 */
public interface AdminDAO extends JpaRepository<Admin,Integer>{

    /**
     * 查询用户
     * @param username
     * @param password
     * @return
     */
    public Admin findByUsernameAndPassword(String username,String password);

}
