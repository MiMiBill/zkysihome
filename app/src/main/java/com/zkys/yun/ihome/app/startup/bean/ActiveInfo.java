package com.zkys.yun.ihome.app.startup.bean;

import org.litepal.crud.LitePalSupport;

/**
 * Created by long yun
 * on 2019/10/25
 * 激活设备的信息
 */
public class ActiveInfo extends LitePalSupport {

    private String username;
    private String phoneNum;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
