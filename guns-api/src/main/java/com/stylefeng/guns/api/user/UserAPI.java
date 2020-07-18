package com.stylefeng.guns.api.user;

/**
 * @ClassName UserAPI
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/17 21:43
 * @Vertion 1.0
 **/
public interface UserAPI {

    int login(String username, String password);

    boolean register(UserModel userModel);

    boolean checkUsername(String username);

    UserInfoModel getUserInfo(int uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);
}
