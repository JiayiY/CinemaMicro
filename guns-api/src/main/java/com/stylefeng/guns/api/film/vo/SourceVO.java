package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SourceVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 12:18
 * @Vertion 1.0
 **/
@Data
public class SourceVO implements Serializable {

    private String sourceId;
    private String sourceName;
    private Boolean isActive;
}
