package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName OrderQueryVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 10:58
 * @Vertion 1.0
 **/
@Data
public class OrderQueryVO implements Serializable {
    private String cinemaId;
    private String filmId;
    private String filmPrice;
}
