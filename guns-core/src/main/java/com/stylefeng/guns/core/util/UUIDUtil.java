package com.stylefeng.guns.core.util;

import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/22 10:51
 * @Vertion 1.0
 **/
public class UUIDUtil {
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
