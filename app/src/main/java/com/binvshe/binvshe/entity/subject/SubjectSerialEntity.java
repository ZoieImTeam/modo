package com.binvshe.binvshe.entity.subject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 专题连载实体类
 */
public class SubjectSerialEntity implements Parcelable {

    /**
     * 连载专题的id
     */
    private String id;

    /**
     * 连载专题的名称
     */
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    public SubjectSerialEntity() {
    }

    protected SubjectSerialEntity(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<SubjectSerialEntity> CREATOR = new Parcelable.Creator<SubjectSerialEntity>() {
        @Override
        public SubjectSerialEntity createFromParcel(Parcel source) {
            return new SubjectSerialEntity(source);
        }

        @Override
        public SubjectSerialEntity[] newArray(int size) {
            return new SubjectSerialEntity[size];
        }
    };
}
