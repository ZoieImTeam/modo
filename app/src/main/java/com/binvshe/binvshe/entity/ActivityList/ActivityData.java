package com.binvshe.binvshe.entity.ActivityList;
//package com.binvshe.binvshe.entity.ActivityList;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class ActivityData {
//
//    @Expose
//    private Integer id;
//    @Expose
//    private String name;
//    @Expose
//    private String typename;
//    @Expose
//    private Integer status;
//    @Expose
//    private String startdate;
//    @Expose
//    private String enddate;
//    @Expose
//    private String closedate;
//    @Expose
//    private Integer scale;
//    @SerializedName("is_money")
//    @Expose
//    private Integer isMoney;
//    @Expose
//    private Integer money;
//    @Expose
//    private String photos;
//    @Expose
//    private String city;
//    @SerializedName("place_x")
//    @Expose
//    private String placeX;
//    @SerializedName("place_y")
//    @Expose
//    private String placeY;
//    @Expose
//    private Integer ticketCount;
//
//    /**
//     * 
//     * @return
//     *     The id
//     */
//    public Integer getId() {
//        return id;
//    }
//
//    /**
//     * 
//     * @param id
//     *     The id
//     */
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    /**
//     * 
//     * @return
//     *     The name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * 
//     * @param name
//     *     The name
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * 
//     * @return
//     *     The typename
//     */
//    public String getTypename() {
//        return typename;
//    }
//
//    /**
//     * 
//     * @param typename
//     *     The typename
//     */
//    public void setTypename(String typename) {
//        this.typename = typename;
//    }
//
//    /**
//     * 
//     * @return
//     *     The status
//     */
//    public Integer getStatus() {
//        return status;
//    }
//
//    /**
//     * 
//     * @param status
//     *     The status
//     */
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    /**
//     * 
//     * @return
//     *     The startdate
//     */
//    public String getStartdate() {
//        return startdate;
//    }
//
//    /**
//     * 
//     * @param startdate
//     *     The startdate
//     */
//    public void setStartdate(String startdate) {
//        this.startdate = startdate;
//    }
//
//    /**
//     * 
//     * @return
//     *     The enddate
//     */
//    public String getEnddate() {
//        return enddate;
//    }
//
//    /**
//     * 
//     * @param enddate
//     *     The enddate
//     */
//    public void setEnddate(String enddate) {
//        this.enddate = enddate;
//    }
//
//    /**
//     * 
//     * @return
//     *     The closedate
//     */
//    public String getClosedate() {
//        return closedate;
//    }
//
//    /**
//     * 
//     * @param closedate
//     *     The closedate
//     */
//    public void setClosedate(String closedate) {
//        this.closedate = closedate;
//    }
//
//    /**
//     * 
//     * @return
//     *     The scale
//     */
//    public Integer getScale() {
//        return scale;
//    }
//
//    /**
//     * 
//     * @param scale
//     *     The scale
//     */
//    public void setScale(Integer scale) {
//        this.scale = scale;
//    }
//
//    /**
//     * 
//     * @return
//     *     The isMoney
//     */
//    public Integer getIsMoney() {
//        return isMoney;
//    }
//
//    /**
//     * 
//     * @param isMoney
//     *     The is_money
//     */
//    public void setIsMoney(Integer isMoney) {
//        this.isMoney = isMoney;
//    }
//
//    /**
//     * 
//     * @return
//     *     The money
//     */
//    public Integer getMoney() {
//        return money;
//    }
//
//    /**
//     * 
//     * @param money
//     *     The money
//     */
//    public void setMoney(Integer money) {
//        this.money = money;
//    }
//
//    /**
//     * 
//     * @return
//     *     The photos
//     */
//    public String getPhotos() {
//        return photos;
//    }
//
//    /**
//     * 
//     * @param photos
//     *     The photos
//     */
//    public void setPhotos(String photos) {
//        this.photos = photos;
//    }
//
//    /**
//     * 
//     * @return
//     *     The city
//     */
//    public String getCity() {
//        return city;
//    }
//
//    /**
//     * 
//     * @param city
//     *     The city
//     */
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    /**
//     * 
//     * @return
//     *     The placeX
//     */
//    public String getPlaceX() {
//        return placeX;
//    }
//
//    /**
//     * 
//     * @param placeX
//     *     The place_x
//     */
//    public void setPlaceX(String placeX) {
//        this.placeX = placeX;
//    }
//
//    /**
//     * 
//     * @return
//     *     The placeY
//     */
//    public String getPlaceY() {
//        return placeY;
//    }
//
//    /**
//     * 
//     * @param placeY
//     *     The place_y
//     */
//    public void setPlaceY(String placeY) {
//        this.placeY = placeY;
//    }
//
//    /**
//     * 
//     * @return
//     *     The ticketCount
//     */
//    public Integer getTicketCount() {
//        return ticketCount;
//    }
//
//    /**
//     * 
//     * @param ticketCount
//     *     The ticketCount
//     */
//    public void setTicketCount(Integer ticketCount) {
//        this.ticketCount = ticketCount;
//    }
//
//}
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivityData {

    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private String typename;
    @Expose
    private Integer status;
    @Expose
    private String startdate;
    @Expose
    private String enddate;
//    @Expose
//    private String closedate;
    @Expose
    private String xy;
    @Expose
    private String gatherdate;
    @Expose
    private String gatherxy;
    @Expose
    private String lable;
    @Expose
    private Integer scale;
    @SerializedName("is_money")
    @Expose
    private Double isMoney;
    @Expose
    private Double money;
    @Expose
    private String photos;
    @Expose
    private String introduces;
    @Expose
    private String trip;
    @Expose
    private String cost;
    @Expose
    private String relief;
    @Expose
    private String careful;
    @Expose
    private String share;
    @SerializedName("is_del")
    @Expose
    private Integer isDel;
    @Expose
    private String city;
    @Expose
    private String fillin;
    @SerializedName("admin_id")
    @Expose
    private Integer adminId;
    @SerializedName("place_x")
    @Expose
    private String placeX;
    @SerializedName("place_y")
    @Expose
    private String placeY;
    @Expose
    private String price;
    @Expose
    private String discount;
    @Expose
    private Integer ticketCount;
    @Expose
    private Integer isBuy;



    @SerializedName("price_interval")
    @Expose
    private String priceInterval;

    public Integer getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(Integer isBuy) {
        this.isBuy = isBuy;
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
     *     The typename
     */
    public String getTypename() {
        return typename;
    }

    /**
     *
     * @param typename
     *     The typename
     */
    public void setTypename(String typename) {
        this.typename = typename;
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

    /**
     *
     * @return
     *     The enddate
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     *
     * @param enddate
     *     The enddate
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    /**
     *
     * @return
     *     The closedate
     */
//    public String getClosedate() {
//        return closedate;
//    }
//
//    /**
//     *
//     * @param closedate
//     *     The closedate
//     */
//    public void setClosedate(String closedate) {
//        this.closedate = closedate;
//    }

    /**
     *
     * @return
     *     The xy
     */
    public String getXy() {
        return xy;
    }

    /**
     *
     * @param xy
     *     The xy
     */
    public void setXy(String xy) {
        this.xy = xy;
    }

    /**
     *
     * @return
     *     The gatherdate
     */
    public String getGatherdate() {
        return gatherdate;
    }

    /**
     *
     * @param gatherdate
     *     The gatherdate
     */
    public void setGatherdate(String gatherdate) {
        this.gatherdate = gatherdate;
    }

    /**
     *
     * @return
     *     The gatherxy
     */
    public String getGatherxy() {
        return gatherxy;
    }

    /**
     *
     * @param gatherxy
     *     The gatherxy
     */
    public void setGatherxy(String gatherxy) {
        this.gatherxy = gatherxy;
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
     *     The scale
     */
    public Integer getScale() {
        return scale;
    }

    /**
     *
     * @param scale
     *     The scale
     */
    public void setScale(Integer scale) {
        this.scale = scale;
    }

    /**
     *
     * @return
     *     The isMoney
     */
    public Double getIsMoney() {
        return isMoney;
    }

    /**
     *
     * @param isMoney
     *     The is_money
     */
    public void setIsMoney(Double isMoney) {
        this.isMoney = isMoney;
    }

    /**
     *
     * @return
     *     The money
     */
    public Double getMoney() {
        return money;
    }

    /**
     *
     * @param money
     *     The money
     */
    public void setMoney(Double money) {
        this.money = money;
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
     *     The introduces
     */
    public String getIntroduces() {
        return introduces;
    }

    /**
     *
     * @param introduces
     *     The introduces
     */
    public void setIntroduces(String introduces) {
        this.introduces = introduces;
    }

    /**
     *
     * @return
     *     The trip
     */
    public String getTrip() {
        return trip;
    }

    /**
     *
     * @param trip
     *     The trip
     */
    public void setTrip(String trip) {
        this.trip = trip;
    }

    /**
     *
     * @return
     *     The cost
     */
    public String getCost() {
        return cost;
    }

    /**
     *
     * @param cost
     *     The cost
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     *
     * @return
     *     The relief
     */
    public String getRelief() {
        return relief;
    }

    /**
     *
     * @param relief
     *     The relief
     */
    public void setRelief(String relief) {
        this.relief = relief;
    }

    /**
     *
     * @return
     *     The careful
     */
    public String getCareful() {
        return careful;
    }

    /**
     *
     * @param careful
     *     The careful
     */
    public void setCareful(String careful) {
        this.careful = careful;
    }

    /**
     *
     * @return
     *     The share
     */
    public String getShare() {
        return share;
    }

    /**
     *
     * @param share
     *     The share
     */
    public void setShare(String share) {
        this.share = share;
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
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     *     The fillin
     */
    public String getFillin() {
        return fillin;
    }

    /**
     *
     * @param fillin
     *     The fillin
     */
    public void setFillin(String fillin) {
        this.fillin = fillin;
    }

    /**
     *
     * @return
     *     The adminId
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     *
     * @param adminId
     *     The admin_id
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     *
     * @return
     *     The placeX
     */
    public String getPlaceX() {
        return placeX;
    }

    /**
     *
     * @param placeX
     *     The place_x
     */
    public void setPlaceX(String placeX) {
        this.placeX = placeX;
    }

    /**
     *
     * @return
     *     The placeY
     */
    public String getPlaceY() {
        return placeY;
    }

    /**
     *
     * @param placeY
     *     The place_y
     */
    public void setPlaceY(String placeY) {
        this.placeY = placeY;
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
     *     The discount
     */
    public String getDiscount() {
        return discount;
    }

    /**
     *
     * @param discount
     *     The discount
     */
    public void setDiscount(String discount) {
        this.discount = discount;
    }

    /**
     *
     * @return
     *     The ticketCount
     */
    public Integer getTicketCount() {
        return ticketCount;
    }

    /**
     *
     * @param ticketCount
     *     The ticketCount
     */
    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }


    public String getPriceInterval() {
        return priceInterval;
    }

    public void setPriceInterval(String priceInterval) {
        this.priceInterval = priceInterval;
    }

    @Override
    public String toString() {
        return "ActivityData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typename='" + typename + '\'' +
                ", status=" + status +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
//                ", closedate='" + closedate + '\'' +
                ", xy='" + xy + '\'' +
                ", gatherdate='" + gatherdate + '\'' +
                ", gatherxy='" + gatherxy + '\'' +
                ", lable='" + lable + '\'' +
                ", scale=" + scale +
                ", isMoney=" + isMoney +
                ", money=" + money +
                ", photos='" + photos + '\'' +
                ", introduces='" + introduces + '\'' +
                ", trip='" + trip + '\'' +
                ", cost='" + cost + '\'' +
                ", relief='" + relief + '\'' +
                ", careful='" + careful + '\'' +
                ", share='" + share + '\'' +
                ", isDel=" + isDel +
                ", city='" + city + '\'' +
                ", fillin='" + fillin + '\'' +
                ", adminId=" + adminId +
                ", placeX='" + placeX + '\'' +
                ", placeY='" + placeY + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", ticketCount=" + ticketCount +
                '}';
    }
}

