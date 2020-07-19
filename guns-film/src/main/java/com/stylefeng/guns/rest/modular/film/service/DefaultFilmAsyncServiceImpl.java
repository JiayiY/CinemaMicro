package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.film.FilmAsyncServiceApi;
import com.stylefeng.guns.api.film.vo.ActorVO;
import com.stylefeng.guns.api.film.vo.FilmDescVO;
import com.stylefeng.guns.rest.common.persistence.dao.ActorTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.FilmInfoTMapper;
import com.stylefeng.guns.rest.common.persistence.model.ActorT;
import com.stylefeng.guns.rest.common.persistence.model.FilmInfoT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName DefaultFilmAsyncServiceImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 15:57
 * @Vertion 1.0
 **/
@Component
@Service(interfaceClass = FilmAsyncServiceApi.class)
public class DefaultFilmAsyncServiceImpl implements FilmAsyncServiceApi {

    @Autowired
    private FilmInfoTMapper filmInfoTMapper;

    @Autowired
    private ActorTMapper actorTMapper;

    @Override
    public FilmDescVO getFilmDesc(String filmId) {
        FilmInfoT filmInfoT = getFilmInfo(filmId);
        FilmDescVO filmDescVO = new FilmDescVO();

        filmDescVO.setBiography(filmInfoT.getFilmBiography());
        return filmDescVO;
    }

    @Override
    public ActorVO getDirectorInfo(String filmId) {
        FilmInfoT filmInfoT = getFilmInfo(filmId);

        // 获取导演编号
        Integer directorId = filmInfoT.getDirectorId();
        ActorT actorT = actorTMapper.selectById(directorId);

        ActorVO actorVO = new ActorVO();
        actorVO.setImgAddress(actorT.getActorImg());
        actorVO.setDirectorName(actorT.getActorName());
        return actorVO;
    }

    @Override
    public List<ActorVO> getActors(String filmId) {
        return actorTMapper.getActors(filmId);
    }

    private FilmInfoT getFilmInfo(String filmId) {
        FilmInfoT filmInfoT = new FilmInfoT();
        filmInfoT.setFilmId(filmId);

        filmInfoT = filmInfoTMapper.selectOne(filmInfoT);
        return filmInfoT;
    }
}

