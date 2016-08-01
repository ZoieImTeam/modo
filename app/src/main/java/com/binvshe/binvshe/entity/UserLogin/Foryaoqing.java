package com.binvshe.binvshe.entity.UserLogin;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by chenjy on 2015/12/3.
 */
@DatabaseTable(tableName = "tab_foryaoqing")
public class Foryaoqing {
    @Override
    public String toString() {
        return "Foryaoqing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", sex=" + sex +
                ", head='" + head + '\'' +
                ", auth=" + auth +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", userb=" + userb +
                ", sign='" + sign + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserb() {
        return userb;
    }

    public void setUserb(int userb) {
        this.userb = userb;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @DatabaseField(columnName = "id")
    private int id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "user")
    private String user;
    @DatabaseField(columnName = "sex")
    private int sex;
    @DatabaseField(columnName = "head")
    private String head;
    @DatabaseField(columnName = "auth")
    private int auth;
    @DatabaseField(columnName = "address")
    private String address;
    @DatabaseField(columnName = "company")
    private String company;
    @DatabaseField(columnName = "token")
    private String token;
    @DatabaseField(columnName = "status")
    private int status;
    @DatabaseField(columnName = "userb")
    private int userb;
    @DatabaseField(columnName = "sign")
    private String sign;
    @DatabaseField(columnName = "job")
    private String job;


}
