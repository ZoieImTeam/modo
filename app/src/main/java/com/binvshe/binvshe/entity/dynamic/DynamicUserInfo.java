package com.binvshe.binvshe.entity.dynamic;

/**
 * Created by Administrator on 2016/2/17.
 */
public class DynamicUserInfo {
    private int id;
    private String name;
    private String user;
    private String user_x;
    private String user_y;
    private String head;
    private int sex;
    private String sign;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUser_x(String user_x) {
        this.user_x = user_x;
    }

    public void setUser_y(String user_y) {
        this.user_y = user_y;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getUser_x() {
        return user_x;
    }

    public String getUser_y() {
        return user_y;
    }

    public String getHead() {
        return head;
    }

    public int getSex() {
        return sex;
    }

    public String getSign() {
        return sign;
    }
}