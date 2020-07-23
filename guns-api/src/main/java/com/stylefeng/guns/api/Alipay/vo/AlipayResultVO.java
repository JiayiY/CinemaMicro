package com.stylefeng.guns.api.Alipay.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AlipayResultVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/23 21:29
 * @Vertion 1.0
 **/
@Data
public class AlipayResultVO implements Serializable {

    private String orderId;
    private Integer orderStatus;
    private String orderMsg;
}
