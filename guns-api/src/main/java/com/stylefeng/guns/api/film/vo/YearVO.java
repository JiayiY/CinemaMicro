package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName YearVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 12:18
 * @Vertion 1.0
 **/
@Data
public class YearVO implements Serializable {

    private String yearId;
    private String yearName;
    private Boolean isActive;
}
