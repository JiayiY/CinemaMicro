package com.stylefeng.guns.api.order.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName OrderVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/22 10:15
 * @Vertion 1.0
 **/
@Data
public class OrderVO implements Serializable {

    private String orderId;
    private String filmName;
    private String imgAddress;
    private String fieldTime;
    private String cinemaName;
    private String seatsName;
    private String orderPrice;
    private String orderTimestamp;
    private String orderStatus;
}

