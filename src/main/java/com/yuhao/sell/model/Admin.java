package com.yuhao.sell.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Admin
 *
 * @author CYH
 * @date 2018/4/2
 */
@Data
@Entity
public class Admin {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

}
