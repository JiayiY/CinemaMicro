package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName FilmInfoVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 10:57
 * @Vertion 1.0
 **/
@Data
public class FilmInfoVO implements Serializable {
    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmType;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private List<FilmFieldVO> filmFields;
}
