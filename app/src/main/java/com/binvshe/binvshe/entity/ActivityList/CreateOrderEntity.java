package com.binvshe.binvshe.entity.ActivityList;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/8
 */
public class CreateOrderEntity implements Parcelable {

    private ActivityEntity activity;
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
    private double totalFee;
    private String token;
    private String tradingNo;
    private int useraid;
    private int userbid;
    /**
     * id : 5acc58a5-f45e-4cb5-bbf0-a17d0e215e7e
     * tickets : [{"activityId":90,"cardno":"","cardtype":"","createTime":1470559974000,"del":false,"discount":0,"id":862,"name":"942be604605526882a44f62fa19f5f56","numcode":"T134822566","orderId":1348,"photos":"","price":160,"productId":5,"qrcodeId":"5acc58a5-f45e-4cb5-bbf0-a17d0e215e7e","timestamp":1470559974000,"token":"","used":false,"user":314,"usermobile":""}]
     */

    private List<SubOrdersEntity> subOrders;

    public ActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(ActivityEntity activity) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.activity, flags);
        dest.writeInt(this.activityId);
        dest.writeLong(this.createTime);
        dest.writeString(this.detail);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.num);
        dest.writeString(this.orderNo);
        dest.writeDouble(this.price);
        dest.writeInt(this.productId);
        dest.writeInt(this.purpose);
        dest.writeInt(this.status);
        dest.writeLong(this.timestamp);
        dest.writeString(this.token);
        dest.writeDouble(this.totalFee);
        dest.writeString(this.tradingNo);
        dest.writeInt(this.useraid);
        dest.writeInt(this.userbid);
        dest.writeList(this.subOrders);
    }

    public CreateOrderEntity() {
    }

    protected CreateOrderEntity(Parcel in) {
        this.activity = in.readParcelable(ActivityEntity.class.getClassLoader());
        this.activityId = in.readInt();
        this.createTime = in.readLong();
        this.detail = in.readString();
        this.id = in.readInt();
        this.name = in.readString();
        this.num = in.readInt();
        this.orderNo = in.readString();
        this.price = in.readDouble();
        this.productId = in.readInt();
        this.purpose = in.readInt();
        this.status = in.readInt();
        this.timestamp = in.readLong();
        this.token = in.readString();
        this.totalFee = in.readDouble();
        this.tradingNo = in.readString();
        this.useraid = in.readInt();
        this.userbid = in.readInt();
        this.subOrders = new ArrayList<SubOrdersEntity>();
        in.readList(this.subOrders, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<CreateOrderEntity> CREATOR = new Parcelable.Creator<CreateOrderEntity>() {
        public CreateOrderEntity createFromParcel(Parcel source) {
            return new CreateOrderEntity(source);
        }

        public CreateOrderEntity[] newArray(int size) {
            return new CreateOrderEntity[size];
        }
    };
}
