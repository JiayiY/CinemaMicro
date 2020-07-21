package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CinemaVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 10:57
 * @Vertion 1.0
 **/
@Data
public class CinemaVO implements Serializable {
    private String uuid;
    private String cinemaName;
    private String address;
    private String minimumPrice;
}
