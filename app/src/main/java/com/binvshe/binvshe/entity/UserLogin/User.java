package com.binvshe.binvshe.entity.UserLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_user")
public class User {

    public static final String SEX_UNKNOW = "0";
    public static final String SEX_MAN = "1";
    public static final String SEX_WOMAN = "2";

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Expose
    private String uldate;

    @Override
    public String toString() {
        return "User{" +
                "uldate='" + uldate + '\'' +
                ", moblie='" + moblie + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", head='" + head + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", interest='" + interest + '\'' +
                ", lable='" + lable + '\'' +
                ", school='" + school + '\'' +
                ", comefrom='" + comefrom + '\'' +
                ", token='" + token + '\'' +
                ", tokenname='" + tokenname + '\'' +
                ", status=" + status +
                ", newNo='" + newNo + '\'' +
                ", keyNo='" + keyNo + '\'' +
                ", atteation=" + atteation +
                ", interestids='" + interestids + '\'' +
                ", atteations=" + atteations +
                ", user_x='" + userX + '\'' +
                ", user_y='" + userY + '\'' +
                ", tomoney=" + tomoney +
                ", sign='" + sign + '\'' +
                ", job='" + job + '\'' +
                ", constellation='" + constellation + '\'' +
                ", userState=" + userState +
                ", auth=" + auth +
                ", rongtoken='" + rongtoken + '\'' +
                '}';
    }

    @Expose
    @DatabaseField(columnName = "moblie")
    private String moblie;

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getUldate() {
        return uldate;
    }

    public void setUldate(String uldate) {
        this.uldate = uldate;
    }

    @Expose
    @DatabaseField(columnName = "age")
    private Integer age;
    @Expose
    @DatabaseField(columnName = "id")
    private Integer id;
    @DatabaseField(columnName = "name")
    @Expose
    private String name;
    @DatabaseField(columnName = "user")
    @Expose
    private String user;
    @DatabaseField(columnName = "password")
    @Expose
    private String password;
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
    @DatabaseField(columnName = "interest")
    @Expose
    private String interest;
    @DatabaseField(columnName = "lable")
    @Expose
    private String lable;
    @DatabaseField(columnName = "school")
    @Expose
    private String school;
    @DatabaseField(columnName = "comefrom")
    @Expose
    private String comefrom;
    @DatabaseField(columnName = "token")
    @Expose
    private String token;
    @DatabaseField(columnName = "tokenname")
    @Expose
    private String tokenname;
    @DatabaseField(columnName = "status")
    @Expose
    private Integer status;
    @DatabaseField(columnName = "newNo")
    @Expose
    private String newNo;
    @DatabaseField(columnName = "keyNo")
    @Expose
    private String keyNo;
    @DatabaseField(columnName = "atteation")
    @Expose
    private Integer atteation;
    @DatabaseField(columnName = "interestids")
    @Expose
    private String interestids;
    @DatabaseField(columnName = "atteations")
    @Expose
    private Integer atteations;
    @DatabaseField(columnName = "user_x")
    @SerializedName("user_x")
    @Expose
    private String userX;
    @DatabaseField(columnName = "user_y")
    @SerializedName("user_y")
    @Expose
    private String userY;
    @DatabaseField(columnName = "tomoney")
    @Expose
    private Integer tomoney;
    @DatabaseField(columnName = "sign")
    @Expose
    private String sign;
    @DatabaseField(columnName = "job")
    @Expose
    private String job;
    @DatabaseField(columnName = "constellation")
    @Expose
    private String constellation;
    @DatabaseField(columnName = "user_state")
    @SerializedName("user_state")
    @Expose
    private Integer userState;
    @DatabaseField(columnName = "auth")
    @Expose
    private Integer auth;
    @DatabaseField(columnName = "rongtoken")
    @Expose
    private String rongtoken;

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
     *     The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     *     The password
     */
    public void setPassword(String password) {
        this.password = password;
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
     *     The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The company
     */
    public String getCompany() {
        return company;
    }

    /**
     * 
     * @param company
     *     The company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 
     * @return
     *     The interest
     */
    public String getInterest() {
        return interest;
    }

    /**
     * 
     * @param interest
     *     The interest
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     * 
     * @return
     *     The lable
     */
    public String getLable() {
        return lable;
    }

    /**
     * 
     * @param lable
     *     The lable
     */
    public void setLable(String lable) {
        this.lable = lable;
    }

    /**
     * 
     * @return
     *     The school
     */
    public String getSchool() {
        return school;
    }

    /**
     * 
     * @param school
     *     The school
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * 
     * @return
     *     The comefrom
     */
    public String getComefrom() {
        return comefrom;
    }

    /**
     * 
     * @param comefrom
     *     The comefrom
     */
    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    /**
     * 
     * @return
     *     The token
     */
    public String getToken() {
        return token;
    }

    /**
     * 
     * @param token
     *     The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 
     * @return
     *     The tokenname
     */
    public String getTokenname() {
        return tokenname;
    }

    /**
     * 
     * @param tokenname
     *     The tokenname
     */
    public void setTokenname(String tokenname) {
        this.tokenname = tokenname;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The newNo
     */
    public String getNewNo() {
        return newNo;
    }

    /**
     * 
     * @param newNo
     *     The newNo
     */
    public void setNewNo(String newNo) {
        this.newNo = newNo;
    }

    /**
     * 
     * @return
     *     The keyNo
     */
    public String getKeyNo() {
        return keyNo;
    }

    /**
     * 
     * @param keyNo
     *     The keyNo
     */
    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }

    /**
     * 
     * @return
     *     The atteation
     */
    public Integer getAtteation() {
        return atteation;
    }

    /**
     * 
     * @param atteation
     *     The atteation
     */
    public void setAtteation(Integer atteation) {
        this.atteation = atteation;
    }

    /**
     * 
     * @return
     *     The interestids
     */
    public String getInterestids() {
        return interestids;
    }

    /**
     * 
     * @param interestids
     *     The interestids
     */
    public void setInterestids(String interestids) {
        this.interestids = interestids;
    }

    /**
     * 
     * @return
     *     The atteations
     */
    public Integer getAtteations() {
        return atteations;
    }

    /**
     * 
     * @param atteations
     *     The atteations
     */
    public void setAtteations(Integer atteations) {
        this.atteations = atteations;
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
     *     The tomoney
     */
    public Integer getTomoney() {
        return tomoney;
    }

    /**
     * 
     * @param tomoney
     *     The tomoney
     */
    public void setTomoney(Integer tomoney) {
        this.tomoney = tomoney;
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
     *     The job
     */
    public String getJob() {
        return job;
    }

    /**
     * 
     * @param job
     *     The job
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 
     * @return
     *     The constellation
     */
    public String getConstellation() {
        return constellation;
    }

    /**
     * 
     * @param constellation
     *     The constellation
     */
    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    /**
     * 
     * @return
     *     The userState
     */
    public Integer getUserState() {
        return userState;
    }

    /**
     * 
     * @param userState
     *     The user_state
     */
    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    /**
     * 
     * @return
     *     The auth
     */
    public Integer getAuth() {
        return auth;
    }

    /**
     * 
     * @param auth
     *     The auth
     */
    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    /**
     * 
     * @return
     *     The rongtoken
     */
    public String getRongtoken() {
        return rongtoken;
    }

    /**
     * 
     * @param rongtoken
     *     The rongtoken
     */
    public void setRongtoken(String rongtoken) {
        this.rongtoken = rongtoken;
    }

}
