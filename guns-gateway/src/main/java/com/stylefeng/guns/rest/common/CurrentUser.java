package com.stylefeng.guns.rest.common;

import com.stylefeng.guns.api.user.UserInfoModel;

/**
 * @ClassName CommonUser
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/18 9:51
 * @Vertion 1.0
 **/
public class CurrentUser {
/*
    // 线程绑定的存储空间
    private static final ThreadLocal<UserInfoModel> THREAD_LOCAL = new ThreadLocal<>();

    *//**
     * @Author yangjiayi
     * @Description //将用户信息放入存储空间
     * @Date 9:54 2020/7/18
     * @param userInfoModel
     * @return void
     *//*
    public static void saveUserInfo(UserInfoModel userInfoModel){
        THREAD_LOCAL.set(userInfoModel);
    }

    */
    /**
     * @Author yangjiayi
     * @Description //取出
     * @Date 9:55 2020/7/18
     * @param
     * @return com.stylefeng.guns.api.user.UserInfoModel
     *//*
    public static UserInfoModel getCurrentUser(){
        return THREAD_LOCAL.get();
    }
    */

    // 仅存储userId 防止存储整个对象占用过多的内存空间 OOM
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void saveUserId(String userId) {
        THREAD_LOCAL.set(userId);
    }

    public static String getCurrentUser() {
        return THREAD_LOCAL.get();
    }
}
