package com.binvshe.binvshe.entity.subject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/3/20.
 */
public class SubjectTypeEntity implements Parcelable {

    private int icon;
    private String name;
    private String id;
    private String pid;


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.icon);
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.pid);
    }

    public SubjectTypeEntity() {
    }

    protected SubjectTypeEntity(Parcel in) {
        this.icon = in.readInt();
        this.name = in.readString();
        this.id = in.readString();
        this.pid = in.readString();
    }

    public static final Parcelable.Creator<SubjectTypeEntity> CREATOR = new Parcelable.Creator<SubjectTypeEntity>() {
        public SubjectTypeEntity createFromParcel(Parcel source) {
            return new SubjectTypeEntity(source);
        }

        public SubjectTypeEntity[] newArray(int size) {
            return new SubjectTypeEntity[size];
        }
    };
}
