package com.yuhao.sell.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * SellerDTO
 *
 * @author CYH
 * @date 2018/4/19
 */
@Data
public class SellerDTO{


    private String name;

    private BigDecimal turnover;

    public SellerDTO(String name, BigDecimal turnover) {
        this.name = name;
        this.turnover = turnover;
    }



}
