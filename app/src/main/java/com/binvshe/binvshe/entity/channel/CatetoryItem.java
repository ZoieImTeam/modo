package com.binvshe.binvshe.entity.channel;

/**
 * Created by Administrator on 2016/2/19.
 */
public class CatetoryItem {
    private int id;
    private String name;
    private int pid;
    private String photos;
    private int is_del;
    private String createdate;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPid() {
        return pid;
    }

    public String getPhotos() {
        return photos;
    }

    public int getIs_del() {
        return is_del;
    }

    public String getCreatedate() {
        return createdate;
    }
}