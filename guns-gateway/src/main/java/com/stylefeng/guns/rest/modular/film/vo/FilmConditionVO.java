package com.stylefeng.guns.rest.modular.film.vo;

import com.stylefeng.guns.api.film.vo.CatVO;
import com.stylefeng.guns.api.film.vo.SourceVO;
import com.stylefeng.guns.api.film.vo.YearVO;
import lombok.Data;

import java.util.List;

/**
 * @ClassName FilmConditionVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 12:55
 * @Vertion 1.0
 **/
@Data
public class FilmConditionVO {

    private List<CatVO> catInfo;

    private List<SourceVO> sourceInfo;

    private List<YearVO> yearInfo;
}
