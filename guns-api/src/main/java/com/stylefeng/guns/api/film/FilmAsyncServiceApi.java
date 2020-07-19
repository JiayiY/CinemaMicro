package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.vo.ActorVO;
import com.stylefeng.guns.api.film.vo.FilmDescVO;

import java.util.List;

/**
 * @ClassName FilmAsyncServiceApi
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 12:18
 * @Vertion 1.0
 **/
public interface FilmAsyncServiceApi {

    /**
     * 获取影片描述信息
     * @param filmId film_t中的UUID
     * @return 影片描述VO
     */
    FilmDescVO getFilmDesc(String filmId);

    /**
     * 获取导演信息
     * @param filmId film_t中的UUID
     * @return 导演信息VO
     */
    ActorVO getDirectorInfo(String filmId);

    /**
     * 获取演员信息列表
     * @param filmId film_t中的UUID
     * @return 演员信息列表
     */
    List<ActorVO> getActors(String filmId);
}
