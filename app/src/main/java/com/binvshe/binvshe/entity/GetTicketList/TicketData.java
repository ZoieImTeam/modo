package com.binvshe.binvshe.entity.GetTicketList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TicketData implements Serializable{

    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private Integer user;
    @Expose
    private String price;
    @Expose
    private String photos;
    @Expose
    private Integer qrcode;
    @SerializedName("is_del")
    @Expose
    private Integer isDel;
    @Expose
    private Integer activity;
    @Expose
    private String cardtype;
    @Expose
    private String cardno;
    @Expose
    private String usermobile;
    @Expose
    private Integer discount;
    @Expose
    private String token;
//    @Expose
//    private Createdate createdate;
    @Expose
    private String numcode;
    @Expose
    private String activityName;
    @Expose
    private String startdate;
    
    private String enddate;

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
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
    public Integer getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 
     * @return
     *     The photos
     */
    public String getPhotos() {
        return photos;
    }

    /**
     * 
     * @param photos
     *     The photos
     */
    public void setPhotos(String photos) {
        this.photos = photos;
    }

    /**
     * 
     * @return
     *     The qrcode
     */
    public Integer getQrcode() {
        return qrcode;
    }

    /**
     * 
     * @param qrcode
     *     The qrcode
     */
    public void setQrcode(Integer qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * 
     * @return
     *     The isDel
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 
     * @param isDel
     *     The is_del
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 
     * @return
     *     The activity
     */
    public Integer getActivity() {
        return activity;
    }

    /**
     * 
     * @param activity
     *     The activity
     */
    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    /**
     * 
     * @return
     *     The cardtype
     */
    public String getCardtype() {
        return cardtype;
    }

    /**
     * 
     * @param cardtype
     *     The cardtype
     */
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    /**
     * 
     * @return
     *     The cardno
     */
    public String getCardno() {
        return cardno;
    }

    /**
     * 
     * @param cardno
     *     The cardno
     */
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    /**
     * 
     * @return
     *     The usermobile
     */
    public String getUsermobile() {
        return usermobile;
    }

    /**
     * 
     * @param usermobile
     *     The usermobile
     */
    public void setUsermobile(String usermobile) {
        this.usermobile = usermobile;
    }

    /**
     * 
     * @return
     *     The discount
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * 
     * @param discount
     *     The discount
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
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
     *     The createdate
     */
//    public Createdate getCreatedate() {
//        return createdate;
//    }

    /**
     * 
     * @param createdate
     *     The createdate
     */
//    public void setCreatedate(Createdate createdate) {
//        this.createdate = createdate;
//    }

    /**
     * 
     * @return
     *     The numcode
     */
    public String getNumcode() {
        return numcode;
    }

    /**
     * 
     * @param numcode
     *     The numcode
     */
    public void setNumcode(String numcode) {
        this.numcode = numcode;
    }

    /**
     * 
     * @return
     *     The activityName
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * 
     * @param activityName
     *     The activityName
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * 
     * @return
     *     The startdate
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * 
     * @param startdate
     *     The startdate
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    @Override
    public String toString() {
        return "TicketData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", price=" + price +
                ", photos='" + photos + '\'' +
                ", qrcode=" + qrcode +
                ", isDel=" + isDel +
                ", activity=" + activity +
                ", cardtype=" + cardtype +
                ", cardno=" + cardno +
                ", usermobile=" + usermobile +
                ", discount=" + discount +
                ", token=" + token +
//                ", createdate=" + createdate +
                ", numcode='" + numcode + '\'' +
                ", activityName='" + activityName + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                '}';
    }
}
