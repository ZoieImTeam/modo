package com.binvshe.binvshe.http.response.data;

import com.binvshe.binvshe.entity.UserLogin.User;
import com.binvshe.binvshe.entity.UserLogin.UserLogin;
import com.binvshe.binvshe.http.response.BaseResponse;

/**
 * 获取6个人中心（关注数，粉丝数,用户基本信息）Response
 */
public class GetUserCenterData{

    private String fans;

    private String attention;

    private User userinfo;

    public User getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(User userinfo) {
        this.userinfo = userinfo;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    @Override
    public String toString() {
        return "GetUserCenterData{" +
                "fans='" + fans + '\'' +
                ", attention='" + attention + '\'' +
                ", userinfo=" + userinfo +
                '}';
    }
}
