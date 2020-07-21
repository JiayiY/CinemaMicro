package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName BrandVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 10:56
 * @Vertion 1.0
 **/
@Data
public class BrandVO implements Serializable {
    private String brandId;
    private String brandName;
    private Boolean isActive;
}
