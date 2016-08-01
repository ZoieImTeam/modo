package com.binvshe.binvshe.entity.dynamic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/2/17.
 */
public class DynamicSpe implements Parcelable {
    private int specialid;
    private String systype;
    private String typename;
    private int pid;
    private String pname;
    private String name;
    private int user;
    private String username;
    private String head;
    private String photos;
    private String lable;
    private String createdate;
    private String desc;
    private int browsenumber;
    private int likeCount;

    private String original;
    private String roleinfo;
    private String cameraman;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getRoleinfo() {
        return roleinfo;
    }

    public void setRoleinfo(String roleinfo) {
        this.roleinfo = roleinfo;
    }

    public String getCameraman() {
        return cameraman;
    }

    public void setCameraman(String cameraman) {
        this.cameraman = cameraman;
    }

    public void setSpecialid(int specialid) {
        this.specialid = specialid;
    }

    public void setSystype(String systype) {
        this.systype = systype;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setBrowsenumber(int browsenumber) {
        this.browsenumber = browsenumber;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getSpecialid() {
        return specialid;
    }

    public String getSystype() {
        return systype;
    }

    public String getTypename() {
        return typename;
    }

    public int getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public String getName() {
        return name;
    }

    public int getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getHead() {
        return head;
    }

    public String getPhotos() {
        return photos;
    }

    public String getLable() {
        return lable;
    }

    public String getCreatedate() {
        return createdate;
    }

    public String getDesc() {
        return desc;
    }

    public int getBrowsenumber() {
        return browsenumber;
    }

    public int getLikeCount() {
        return likeCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.specialid);
        dest.writeString(this.systype);
        dest.writeString(this.typename);
        dest.writeInt(this.pid);
        dest.writeString(this.pname);
        dest.writeString(this.name);
        dest.writeInt(this.user);
        dest.writeString(this.username);
        dest.writeString(this.head);
        dest.writeString(this.photos);
        dest.writeString(this.lable);
        dest.writeString(this.createdate);
        dest.writeString(this.desc);
        dest.writeInt(this.browsenumber);
        dest.writeInt(this.likeCount);
        dest.writeString(this.original);
        dest.writeString(this.roleinfo);
        dest.writeString(this.cameraman);
    }

    public DynamicSpe() {
    }

    protected DynamicSpe(Parcel in) {
        this.specialid = in.readInt();
        this.systype = in.readString();
        this.typename = in.readString();
        this.pid = in.readInt();
        this.pname = in.readString();
        this.name = in.readString();
        this.user = in.readInt();
        this.username = in.readString();
        this.head = in.readString();
        this.photos = in.readString();
        this.lable = in.readString();
        this.createdate = in.readString();
        this.desc = in.readString();
        this.browsenumber = in.readInt();
        this.likeCount = in.readInt();
        this.original = in.readString();
        this.roleinfo = in.readString();
        this.cameraman = in.readString();
    }

    public static final Parcelable.Creator<DynamicSpe> CREATOR = new Parcelable.Creator<DynamicSpe>() {
        public DynamicSpe createFromParcel(Parcel source) {
            return new DynamicSpe(source);
        }

        public DynamicSpe[] newArray(int size) {
            return new DynamicSpe[size];
        }
    };
}