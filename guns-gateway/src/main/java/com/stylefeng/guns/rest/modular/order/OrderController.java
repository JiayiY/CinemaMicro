package com.stylefeng.guns.rest.modular.order;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.order.OrderServiceApi;
import com.stylefeng.guns.api.order.vo.OrderVO;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/22 10:05
 * @Vertion 1.0
 **/
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/order/")
public class OrderController {

    private static final String IMG_PRE = "";


    @Reference(interfaceClass = OrderServiceApi.class, check = false, group = "orderB")
    private OrderServiceApi orderServiceApiB;

    @Reference(interfaceClass = OrderServiceApi.class, check = false, group = "orderA")
    private OrderServiceApi orderServiceApiA;

    @RequestMapping(value = "buyTickets", method = RequestMethod.POST)
    public ResponseVO<?> buyTickets(Integer fieldId, String soldSeats, String seatsName) {
        try {
            // 获取当前用户的信息
            String userId = CurrentUser.getCurrentUser();
            if (userId == null || userId.trim().length() == 0) {
                return ResponseVO.serviceFail("用户未登录");
            }
            // 验证所购买的票是否为真
            boolean isTrue = orderServiceApiB.isTrueSeats(fieldId, soldSeats);
            // 检查所购买的票是否已售出
            boolean isSold = orderServiceApiB.isSoldSeats(fieldId, soldSeats);

            // 验证上面两个条件，当所购买的票为真，且未售出时，才创建订单
            if (isTrue && !isSold) {
                // 创建订单信息
                OrderVO orderVO = orderServiceApiB.saveOrderInfo(fieldId, soldSeats, seatsName, Integer.parseInt(userId));
                if (orderVO == null) {
                    log.error("购票未成功");
                    return ResponseVO.serviceFail("购票未成功");
                } else {
                    return ResponseVO.success(orderVO);
                }
            } else {
                return ResponseVO.serviceFail("订单中的座位编号不存在或已售出");
            }
        } catch (Exception e) {
            log.error("购票业务异常", e);
            return ResponseVO.serviceFail("购票业务异常");
        }
    }

    @RequestMapping(value = "getOrderInfo", method = RequestMethod.POST)
    public ResponseVO<?> getOrderInfo(@RequestParam(name = "nowPage", required = false, defaultValue = "1") Integer nowPage,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        // 获取当前用户的信息
        String userId = CurrentUser.getCurrentUser();
        if (userId == null || userId.trim().length() == 0) {
            return ResponseVO.serviceFail("用户未登录");
        }
        // 获取当前用户的订单
        Page<OrderVO> page = new Page<>(nowPage, pageSize);
        Page<OrderVO> orderVOPageB = orderServiceApiB.getOrderVOListByUserId(Integer.parseInt(userId), page);
        Page<OrderVO> orderVOPageA = orderServiceApiA.getOrderVOListByUserId(Integer.parseInt(userId), page);

        // 分组聚合
        int totalPages = (int) (orderVOPageB.getPages() + orderVOPageA.getPages());
        List<OrderVO> orderVOList = new ArrayList<>();
        orderVOList.addAll(orderVOPageB.getRecords());
        orderVOList.addAll(orderVOPageA.getRecords());

        return ResponseVO.success(nowPage, totalPages, IMG_PRE, orderVOList);
    }

}

