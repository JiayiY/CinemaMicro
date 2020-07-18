package com.stylefeng.guns.api.user;

import java.io.Serializable;

/**
 * @ClassName UserModel
 * @Description 用于封装注册时的对象 在gateway和user模块间传输
 * @Author yjy
 * @Date 2020/7/17 22:36
 * @Vertion 1.0
 **/
public class UserModel implements Serializable {

    private String username;

    private String password;

    private String email;

    private String phone;

    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
