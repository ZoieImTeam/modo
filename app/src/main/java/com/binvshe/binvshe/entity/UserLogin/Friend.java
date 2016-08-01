package com.binvshe.binvshe.entity.UserLogin;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tab_friend")
public class Friend {
    @DatabaseField(columnName = "id")
    @Expose
    private Integer id;
    @DatabaseField(columnName = "name")
    @Expose
    private String name;
    @DatabaseField(columnName = "user")
    @Expose
    private String user;
    @DatabaseField(columnName = "sex")
    @Expose
    private Integer sex;
    @DatabaseField(columnName = "head")
    @Expose
    private String head;
    @DatabaseField(columnName = "address")
    @Expose
    private String address;
    @DatabaseField(columnName = "company")
    @Expose
    private String company;
    @DatabaseField(columnName = "token")
    @Expose
    private String token;
    @DatabaseField(columnName = "status")
    @Expose
    private Integer status;
    @DatabaseField(columnName = "userb")
    @Expose
    private Integer userb;
    @DatabaseField(columnName = "sign")
    @Expose
    private String sign;
    @DatabaseField(columnName = "job")
    @Expose
    private String job;
    @DatabaseField(columnName = "auth")
    @Expose
    private Integer auth;

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", sex=" + sex +
                ", head='" + head + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", userb=" + userb +
                ", sign='" + sign + '\'' +
                ", job='" + job + '\'' +
                ", auth=" + auth +
                '}';
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return The sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * @param sex The sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return The head
     */
    public String getHead() {
        return head;
    }

    /**
     * @param head The head
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * @return The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return The company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company The company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return The token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return The userb
     */
    public Integer getUserb() {
        return userb;
    }

    /**
     * @param userb The userb
     */
    public void setUserb(Integer userb) {
        this.userb = userb;
    }

    /**
     * @return The sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign The sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * @return The job
     */
    public String getJob() {
        return job;
    }

    /**
     * @param job The job
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * @return The auth
     */
    public Integer getAuth() {
        return auth;
    }

    /**
     * @param auth The auth
     */
    public void setAuth(Integer auth) {
        this.auth = auth;
    }

}
