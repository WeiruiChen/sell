package com.yuhao.sell.dto;

import lombok.Data;

/**
 * CartDTO
 *
 * @author CYH
 * @date 2018/3/27
 */
@Data
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
