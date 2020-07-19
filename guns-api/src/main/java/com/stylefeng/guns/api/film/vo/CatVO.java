package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CatVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 12:18
 * @Vertion 1.0
 **/
@Data
public class CatVO implements Serializable {

    private String catId;
    private String catName;
    private Boolean isActive;
}
