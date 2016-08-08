package com.binvshe.binvshe.entity.ActivityList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/8
 */
public  class ActivityEntity implements Parcelable {
    private int admin_id;
    private String careful;
    private int city;
    private long closedate;
    private String cost;
    private boolean del;
    private long enddate;
    private String fillin;
    private String gatherdate;
    private String gatherxy;
    private int id;
    private String introduces;
    private int is_money;
    private String lable;
    private int machine;
    private int money;
    private String name;
    private String photos;
    private String place_x;
    private String place_y;
    private String relief;
    private int scale;
    private String share;
    private long startdate;
    private int status;
    private String trip;
    private int type;
    private String xy;

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getCareful() {
        return careful;
    }

    public void setCareful(String careful) {
        this.careful = careful;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public long getClosedate() {
        return closedate;
    }

    public void setClosedate(long closedate) {
        this.closedate = closedate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public long getEnddate() {
        return enddate;
    }

    public void setEnddate(long enddate) {
        this.enddate = enddate;
    }

    public String getFillin() {
        return fillin;
    }

    public void setFillin(String fillin) {
        this.fillin = fillin;
    }

    public String getGatherdate() {
        return gatherdate;
    }

    public void setGatherdate(String gatherdate) {
        this.gatherdate = gatherdate;
    }

    public String getGatherxy() {
        return gatherxy;
    }

    public void setGatherxy(String gatherxy) {
        this.gatherxy = gatherxy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroduces() {
        return introduces;
    }

    public void setIntroduces(String introduces) {
        this.introduces = introduces;
    }

    public int getIs_money() {
        return is_money;
    }

    public void setIs_money(int is_money) {
        this.is_money = is_money;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public int getMachine() {
        return machine;
    }

    public void setMachine(int machine) {
        this.machine = machine;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getPlace_x() {
        return place_x;
    }

    public void setPlace_x(String place_x) {
        this.place_x = place_x;
    }

    public String getPlace_y() {
        return place_y;
    }

    public void setPlace_y(String place_y) {
        this.place_y = place_y;
    }

    public String getRelief() {
        return relief;
    }

    public void setRelief(String relief) {
        this.relief = relief;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public long getStartdate() {
        return startdate;
    }

    public void setStartdate(long startdate) {
        this.startdate = startdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getXy() {
        return xy;
    }

    public void setXy(String xy) {
        this.xy = xy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.admin_id);
        dest.writeString(this.careful);
        dest.writeInt(this.city);
        dest.writeLong(this.closedate);
        dest.writeString(this.cost);
        dest.writeByte(del ? (byte) 1 : (byte) 0);
        dest.writeLong(this.enddate);
        dest.writeString(this.fillin);
        dest.writeString(this.gatherdate);
        dest.writeString(this.gatherxy);
        dest.writeInt(this.id);
        dest.writeString(this.introduces);
        dest.writeInt(this.is_money);
        dest.writeString(this.lable);
        dest.writeInt(this.machine);
        dest.writeInt(this.money);
        dest.writeString(this.name);
        dest.writeString(this.photos);
        dest.writeString(this.place_x);
        dest.writeString(this.place_y);
        dest.writeString(this.relief);
        dest.writeInt(this.scale);
        dest.writeString(this.share);
        dest.writeLong(this.startdate);
        dest.writeInt(this.status);
        dest.writeString(this.trip);
        dest.writeInt(this.type);
        dest.writeString(this.xy);
    }

    public ActivityEntity() {
    }

    protected ActivityEntity(Parcel in) {
        this.admin_id = in.readInt();
        this.careful = in.readString();
        this.city = in.readInt();
        this.closedate = in.readLong();
        this.cost = in.readString();
        this.del = in.readByte() != 0;
        this.enddate = in.readLong();
        this.fillin = in.readString();
        this.gatherdate = in.readString();
        this.gatherxy = in.readString();
        this.id = in.readInt();
        this.introduces = in.readString();
        this.is_money = in.readInt();
        this.lable = in.readString();
        this.machine = in.readInt();
        this.money = in.readInt();
        this.name = in.readString();
        this.photos = in.readString();
        this.place_x = in.readString();
        this.place_y = in.readString();
        this.relief = in.readString();
        this.scale = in.readInt();
        this.share = in.readString();
        this.startdate = in.readLong();
        this.status = in.readInt();
        this.trip = in.readString();
        this.type = in.readInt();
        this.xy = in.readString();
    }

    public static final Parcelable.Creator<ActivityEntity> CREATOR = new Parcelable.Creator<ActivityEntity>() {
        public ActivityEntity createFromParcel(Parcel source) {
            return new ActivityEntity(source);
        }

        public ActivityEntity[] newArray(int size) {
            return new ActivityEntity[size];
        }
    };
}