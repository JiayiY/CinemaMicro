package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.vo.CinemaVO;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CinemaListResponseVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 11:10
 * @Vertion 1.0
 **/
@Data
public class CinemaListResponseVO {

    private List<CinemaVO> cinemas;
}
