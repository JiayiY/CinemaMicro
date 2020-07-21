package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName HallTypeVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 10:58
 * @Vertion 1.0
 **/
@Data
public class HallTypeVO implements Serializable {
    private String halltypeId;
    private String halltypeName;
    private Boolean isActive;
}
