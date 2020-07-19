package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ActorVO
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/19 12:18
 * @Vertion 1.0
 **/
@Data
public class ActorVO implements Serializable {

    private String imgAddress;

    private String directorName;

    private String roleName;
}
