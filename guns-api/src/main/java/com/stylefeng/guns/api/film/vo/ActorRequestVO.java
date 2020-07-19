package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ActorRequestVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 12:18
 * @Vertion 1.0
 **/
@Data
public class ActorRequestVO {

    private ActorVO director;

    private List<ActorVO> actors;
}
