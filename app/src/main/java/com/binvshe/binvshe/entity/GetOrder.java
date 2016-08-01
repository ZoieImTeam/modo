package com.binvshe.binvshe.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetOrder {

    @Expose
    private Integer id;
    @Expose
    private String orderNo;
    @Expose
    private String name;
    @SerializedName("total_fee")
    @Expose
    private Double totalFee;
    @Expose
    private Integer status;
    @Expose
    private String detail;
    @Expose
    private Integer useraid;

    private String money;

    private String keyNo;

    public String getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    //    @Expose
//    private Integer userbid;

    public Integer getPurpose() {
        return purpose;
    }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getUseraid() {
        return useraid;
    }

    public void setUseraid(Integer useraid) {
        this.useraid = useraid;
    }


    public String getTradingNo() {
        return tradingNo;
    }

    public void setTradingNo(String tradingNo) {
        this.tradingNo = tradingNo;
    }

//    @Expose
//    private Integer dynamicid;
    @Expose
    private String tradingNo;
    @Expose
    private Integer purpose;

    @Override
    public String toString() {
        return "GetOrder{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", name='" + name + '\'' +
                ", totalFee=" + totalFee +
                ", status=" + status +
                ", detail='" + detail + '\'' +
                ", useraid=" + useraid +
                ", tradingNo='" + tradingNo + '\'' +
                ", purpose=" + purpose +
                '}';
    }
}
