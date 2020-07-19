package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName FilmVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 12:18
 * @Vertion 1.0
 **/
@Data
public class FilmVO implements Serializable {

    private Integer filmNum;
    private Integer nowPage;
    private Integer totalPage;
    private List<FilmInfo> filmInfo;
}
