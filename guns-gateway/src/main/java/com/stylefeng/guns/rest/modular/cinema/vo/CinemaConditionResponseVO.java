package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.vo.AreaVO;
import com.stylefeng.guns.api.cinema.vo.BrandVO;
import com.stylefeng.guns.api.cinema.vo.HallTypeVO;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CinemaConditionResponseVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/21 11:08
 * @Vertion 1.0
 **/
@Data
public class CinemaConditionResponseVO {
    private List<BrandVO> brandList;
    private List<AreaVO> areaList;
    private List<HallTypeVO> halltypeList;

}
