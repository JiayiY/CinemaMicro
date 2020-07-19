package com.stylefeng.guns.rest.modular.film.vo;

import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;
import lombok.Data;

import java.util.List;

/**
 * @ClassName FilmIndexVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 11:55
 * @Vertion 1.0
 **/
@Data
public class FilmIndexVO {

    private List<BannerVO> banners;

    private FilmVO hotFilms;

    private FilmVO soonFilms;

    private List<FilmInfo> boxRanking;

    private List<FilmInfo> expectRanking;

    private List<FilmInfo> top100;
}
