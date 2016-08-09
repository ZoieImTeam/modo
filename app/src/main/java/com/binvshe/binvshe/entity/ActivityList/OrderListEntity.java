package com.binvshe.binvshe.entity.ActivityList;

import java.util.List;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/9
 */
public class OrderListEntity {
    private Object activity;
    private int activityId;
    private long createTime;
    private String detail;
    private int id;
    private String name;
    private int num;
    private String orderNo;
    private double price;
    private int productId;
    private int purpose;
    private int status;
    private long timestamp;
    private String token;
    private double totalFee;
    private String tradingNo;
    private int useraid;
    private int userbid;
    private List<SubOrdersEntity> subOrders;

    public Object getActivity() {
        return activity;
    }

    public void setActivity(Object activity) {
        this.activity = activity;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPurpose() {
        return purpose;
    }

    public void setPurpose(int purpose) {
        this.purpose = purpose;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public String getTradingNo() {
        return tradingNo;
    }

    public void setTradingNo(String tradingNo) {
        this.tradingNo = tradingNo;
    }

    public int getUseraid() {
        return useraid;
    }

    public void setUseraid(int useraid) {
        this.useraid = useraid;
    }

    public int getUserbid() {
        return userbid;
    }

    public void setUserbid(int userbid) {
        this.userbid = userbid;
    }

    public List<SubOrdersEntity> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<SubOrdersEntity> subOrders) {
        this.subOrders = subOrders;
    }

}
