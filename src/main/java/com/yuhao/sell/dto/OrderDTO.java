package com.yuhao.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yuhao.sell.enums.OrderStatusEnum;
import com.yuhao.sell.enums.PayStatusEnum;
import com.yuhao.sell.model.OrderDetail;
import com.yuhao.sell.model.OrderMaster;
import com.yuhao.sell.utils.EnumUtil;
import com.yuhao.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * OrderDTO
 *
 * @author CYH
 * @date 2018/3/26
 */
@Data
public class OrderDTO extends OrderMaster{


    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;


    private List<OrderDetail> orderDetails;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(super.getOrderStatus(), OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(super.getPayStatus(), PayStatusEnum.class);
    }


}
