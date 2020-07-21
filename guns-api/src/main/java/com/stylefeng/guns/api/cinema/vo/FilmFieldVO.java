package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName FilmFieldVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 10:57
 * @Vertion 1.0
 **/
@Data
public class FilmFieldVO implements Serializable {
    private String fieldId;
    private String beginTime;
    private String endTime;
    private String language;
    private String hallName;
    private String price;
}
