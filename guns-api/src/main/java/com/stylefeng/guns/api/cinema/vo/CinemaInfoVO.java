package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CinemaInfoVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 10:57
 * @Vertion 1.0
 **/
@Data
public class CinemaInfoVO implements Serializable {
    private String cinemaId;
    private String imgUrl;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaPhone;
}
