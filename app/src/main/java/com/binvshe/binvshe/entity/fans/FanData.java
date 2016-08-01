package com.binvshe.binvshe.entity.fans;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取粉丝/关注的数据结构
 */
public class FanData {

    /**
     * id
     */
    private Integer id;
    /**
     * 用户id
     */
    private String uid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 用户名称
     */
    private String username;
    /**
     * 头像
     */
    private String head;
    /**
     * 签名
     */
    private String sign;
    @Expose
    private Integer age;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 职业
     */
    private String job;


    /**
     * 是否互相关注，0 否，1 是互相关注
     */
    private String isAtten;
    @Expose
    private List<Object> userlable = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */

    /**
     * 
     * @return
     *     The head
     */
    public String getHead() {
        return head;
    }

    /**
     * 
     * @param head
     *     The head
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * 
     * @return
     *     The sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * 
     * @param sign
     *     The sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 
     * @return
     *     The age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 
     * @param age
     *     The age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 
     * @return
     *     The sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 
     * @param sex
     *     The sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 
     * @return
     *     The userlable
     */
    public List<Object> getUserlable() {
        return userlable;
    }

    /**
     * 
     * @param userlable
     *     The userlable
     */
    public void setUserlable(List<Object> userlable) {
        this.userlable = userlable;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIsAtten() {
        return isAtten;
    }

    public void setIsAtten(String isAtten) {
        this.isAtten = isAtten;
    }
}
