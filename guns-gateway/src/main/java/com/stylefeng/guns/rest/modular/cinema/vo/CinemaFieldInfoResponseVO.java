package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.vo.CinemaInfoVO;
import com.stylefeng.guns.api.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.api.cinema.vo.HallInfoVO;
import lombok.Data;

/**
 * @ClassName CinemaFieldInfoResponseVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 11:11
 * @Vertion 1.0
 **/
@Data
public class CinemaFieldInfoResponseVO {
    private FilmInfoVO filmInfo;
    private CinemaInfoVO cinemaInfo;
    private HallInfoVO hallInfo;
}
