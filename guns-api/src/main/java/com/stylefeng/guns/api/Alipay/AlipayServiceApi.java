package com.stylefeng.guns.api.Alipay;

import com.stylefeng.guns.api.Alipay.vo.AlipayInfoVO;
import com.stylefeng.guns.api.Alipay.vo.AlipayResultVO;

/**
 * @ClassName AlipayServiceApi
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/23 21:28
 * @Vertion 1.0
 **/
public interface AlipayServiceApi {

    /**
     * 预下单并获取二维码地址
     * @param orderId 订单编号
     * @return AlipayInfoVO
     */
    AlipayInfoVO getQRCode(String orderId);

    /**
     * 获取订单状态
     * @param orderId 订单编号
     * @return AlipayResultVO
     */
    AlipayResultVO getOrderStatus(String orderId);

}
