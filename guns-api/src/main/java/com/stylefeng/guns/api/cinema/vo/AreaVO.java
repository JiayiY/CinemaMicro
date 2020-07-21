package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AreaVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 10:56
 * @Vertion 1.0
 **/
@Data
public class AreaVO implements Serializable {

    private String areaId;

    private String areaName;

    private Boolean isActive;
}
