package com.binvshe.binvshe.entity.subject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统分类
 */
public class SysTypeEntitiy implements Parcelable {
    private String id;
    private String name;
    private String pid;
    private String photos;
    private String is_del;
    private String createdate;

    private String style;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    private ArrayList<SubjectEntity> list;

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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public ArrayList<SubjectEntity> getList() {
        return list;
    }

    public void setList(ArrayList<SubjectEntity> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.pid);
        dest.writeString(this.photos);
        dest.writeString(this.is_del);
        dest.writeString(this.createdate);
        dest.writeList(this.list);
        dest.writeString(this.style);
    }

    public SysTypeEntitiy() {
    }

    protected SysTypeEntitiy(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.pid = in.readString();
        this.photos = in.readString();
        this.is_del = in.readString();
        this.createdate = in.readString();
        this.list = new ArrayList<SubjectEntity>();
        this.style = in.readString();
        in.readList(this.list, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<SysTypeEntitiy> CREATOR = new Parcelable.Creator<SysTypeEntitiy>() {
        public SysTypeEntitiy createFromParcel(Parcel source) {
            return new SysTypeEntitiy(source);
        }

        public SysTypeEntitiy[] newArray(int size) {
            return new SysTypeEntitiy[size];
        }
    };
}
