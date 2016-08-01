package com.binvshe.binvshe.entity.fans;

/**
 * Created by chenjy on 2016/2/18.
 */
public class Topic {

    private String id;
    private String systype;
    private String typename;
    private String pid;
    private String pname;
    private String name;
    private String user;
    private String photos;
    private String lable;
    private String createdate;
    private String desc;
    private String browsenumber;
    private String likeCount;

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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
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

    @Override
    public String toString() {
        return "Topic{" +
                "id='" + id + '\'' +
                ", systype='" + systype + '\'' +
                ", typename='" + typename + '\'' +
                ", pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", photos='" + photos + '\'' +
                ", lable='" + lable + '\'' +
                ", createdate='" + createdate + '\'' +
                ", desc='" + desc + '\'' +
                ", browsenumber='" + browsenumber + '\'' +
                ", likeCount='" + likeCount + '\'' +
                '}';
    }
}
