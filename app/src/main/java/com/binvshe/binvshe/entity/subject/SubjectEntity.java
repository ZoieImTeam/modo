package com.binvshe.binvshe.entity.subject;

import java.io.Serializable;

/**
 * 专题列表实体类
 * Created by Administrator on 2016/3/13.
 */
public class SubjectEntity implements Serializable{

    /**
     * 专题ID
     */
    private String id;

    /**
     * 系统二级分类id
     */
    private String systype;

    /**
     * 二级分类名
     */
    private String typename;

    /**
     * 一级分类id
     */
    private String pid;

    /**
     * 一级分类名称
     */
    private String pname;

    /**
     * 专题名称
     */
    private String name;

    /**
     * 用户id
     */
    private String user;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户头像
     */
    private String head;

    /**
     * 标签
     */
    private String lable;

    /**
     * 创建时间
     */
    private String createdate;

    /**
     * 简介
     */
    private String desc;

    /**
     * 浏览数
     */
    private String browsenumber;

    /**
     * 点赞数
     */
    private String likeCount;

    /**
     * 封面图
     */
    private String photos;

    /**
     * 是否已点赞 0：未点赞，1：已点赞
     */
    private String isLike;

    /**
     * 展现形式 0:小说
     */
    private String showtype;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystype() {
        return systype;
    }

    public void setSystype(String systype) {
        this.systype = systype;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBrowsenumber() {
        return browsenumber;
    }

    public void setBrowsenumber(String browsenumber) {
        this.browsenumber = browsenumber;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    public String getShowtype() {
        return showtype;
    }

    public void setShowtype(String showtype) {
        this.showtype = showtype;
    }
}
