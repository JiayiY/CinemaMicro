package com.stylefeng.guns.api.Alipay.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AlipayInfoVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/23 21:29
 * @Vertion 1.0
 **/
@Data
public class AlipayInfoVO implements Serializable {

    private String orderId;
    private String QRCodeAddress;
}
