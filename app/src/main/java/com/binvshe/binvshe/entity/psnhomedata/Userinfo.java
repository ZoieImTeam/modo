package com.binvshe.binvshe.entity.psnhomedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Userinfo implements Serializable{

    private static final long serialVersionUID = 1L;

    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private String user;
    @SerializedName("user_x")
    @Expose
    private String userX;
    @SerializedName("user_y")
    @Expose
    private String userY;
    @Expose
    private String head;
    @Expose
    private String bgurl;
    @Expose
    private Integer sex;
    @Expose
    private String sign;
    @Expose
    private String job;
    @Expose
    private String company;
    @Expose
    private String school;
    @Expose
    private String address;
    @Expose
    private String birthday;

    public String getJob() {
        if ("\"\"".equals(job)){
            return "";
        }
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        if ("\"\"".equals(company)){
            return "";
        }
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSchool() {
        if ("\"\"".equals(school)){
            return "";
        }
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAddress() {
        if ("\"\"".equals(address)){
            return "";
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

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
    public String getName() {
        if ("\"\"".equals(name)){
            return "";
        }
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The user
     */
    public String getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The userX
     */
    public String getUserX() {
        return userX;
    }

    /**
     * 
     * @param userX
     *     The user_x
     */
    public void setUserX(String userX) {
        this.userX = userX;
    }

    /**
     * 
     * @return
     *     The userY
     */
    public String getUserY() {
        return userY;
    }

    /**
     * 
     * @param userY
     *     The user_y
     */
    public void setUserY(String userY) {
        this.userY = userY;
    }

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
     *     The bgurl
     */
    public Object getBgurl() {
        return bgurl;
    }

    /**
     * 
     * @param bgurl
     *     The bgurl
     */
    public void setBgurl(String bgurl) {
        this.bgurl = bgurl;
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
     *     The sign
     */
    public String getSign() {
        if ("\"\"".equals(sign)){
            return "";
        }
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

    @Override
    public String toString() {
        return "Userinfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", userX='" + userX + '\'' +
                ", userY='" + userY + '\'' +
                ", head='" + head + '\'' +
                ", bgurl='" + bgurl + '\'' +
                ", sex=" + sex +
                ", sign='" + sign + '\'' +
                ", job='" + job + '\'' +
                ", company='" + company + '\'' +
                ", school='" + school + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
