package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName HallInfoVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 10:57
 * @Vertion 1.0
 **/
@Data
public class HallInfoVO implements Serializable {
    private String hallFieldId;
    private String hallName;
    private String price;
    private String seatFile;
    private String soldSeats;
}
