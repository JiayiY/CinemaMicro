package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserInfoModel;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.UserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.UserT;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PipedReader;
import java.sql.Date;

/**
 * @ClassName UserImpl
 * @Description TODO
 * @Author yjy
 * @Date 2020/7/17 21:44
 * @Vertion 1.0
 **/
@Component
@Service(interfaceClass = UserAPI.class)
public class UserServiceImpl implements UserAPI {

    @Autowired
    private UserTMapper userTMapper;

    @Override
    public int login(String username, String password) {
        UserT userT = new UserT();
        userT.setUserPhone(username);
        UserT ret = userTMapper.selectOne(userT);

        if (ret != null && ret.getUuid() > 0) {
            String encrypt = MD5Util.encrypt(password);
            if (ret.getUserPwd().equals(encrypt)) {
                return ret.getUuid();
            }
        }
        return 0;
    }

    @Override
    public boolean register(UserModel userModel) {
        // 将注册信息实体转化为数据实体
        UserT userT = new UserT();
        userT.setUserName(userModel.getUsername());
        userT.setEmail(userModel.getEmail());
        userT.setAddress(userModel.getAddress());
        userT.setUserPhone(userModel.getPhone());

        // 密码加密
        String encryptPwd = MD5Util.encrypt(userModel.getPassword());
        userT.setUserPwd(encryptPwd);

        // 将数据实体存入数据库
        Integer insert = userTMapper.insert(userT);
        return insert > 0;
    }

    @Override
    public boolean checkUsername(String username) {
        EntityWrapper<UserT> userTEntityWrapper = new EntityWrapper<>();
        userTEntityWrapper.eq("user_name", username);
        Integer count = userTMapper.selectCount(userTEntityWrapper);
        return count == null || count <= 0;
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        UserT userT = userTMapper.selectById(uuid);
        UserInfoModel userInfoModel = do2UserInfo(userT);
        return userInfoModel;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        UserT userT = new UserT();
        BeanUtils.copyProperties(userInfoModel, userT);
        userT.setHeadUrl(userInfoModel.getHeadAddress());
        userT.setUserPhone(userInfoModel.getPhone());
        userT.setUpdateTime(time2Date(System.currentTimeMillis()));
        userT.setLifeState(Integer.valueOf(userInfoModel.getLifeState()));
        userT.setUserSex(userInfoModel.getSex());
        userT.setBeginTime(time2Date(userInfoModel.getBeginTime()));
        Integer ret = userTMapper.updateById(userT);
        if (ret > 0) {
            UserInfoModel userInfo = getUserInfo(userT.getUuid());
            return userInfo;
        } else {
            return userInfoModel;
        }
    }

    private UserInfoModel do2UserInfo(UserT userT) {
        UserInfoModel userInfoModel = new UserInfoModel();
        BeanUtils.copyProperties(userT, userInfoModel);
        userInfoModel.setHeadAddress(userT.getHeadUrl());
        userInfoModel.setPhone(userT.getUserPhone());
        userInfoModel.setUpdateTime(userT.getUpdateTime().getTime());
        userInfoModel.setLifeState("" + userT.getLifeState());
        userInfoModel.setSex(userT.getUserSex());
        userInfoModel.setBeginTime(userT.getBeginTime().getTime());
        return userInfoModel;
    }

    private Date time2Date(long time) {
        Date date = new Date(time);
        return date;
    }
}
