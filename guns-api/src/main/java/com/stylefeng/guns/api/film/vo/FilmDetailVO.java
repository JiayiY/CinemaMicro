package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName FilmDetailVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 12:18
 * @Vertion 1.0
 **/
@Data
public class FilmDetailVO implements Serializable {

    private String filmId;

    private String filmName;

    private String filmEnName;

    private Integer filmStatus;

    private String imgAddress;

    private String score;

    private String scoreNum;

    /**
     * 总票房
     */
    private String totalBox;

    private String info01;

    private String info02;

    private String info03;

    private InfoRequestVO info04;

    private ImgVO imgs;
}
