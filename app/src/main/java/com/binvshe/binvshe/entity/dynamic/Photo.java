package com.binvshe.binvshe.entity.dynamic;

/**
 * Created by Administrator on 2016/2/17.
 */
public class Photo {
    private int photosid;
    private String photourl;
    private String user;
    private String special;
    private int resources;
    private String createdate;
    private String width;
    private String height;
    private int is_del;

    public void setPhotosid(int photosid) {
        this.photosid = photosid;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    public int getPhotosid() {
        return photosid;
    }

    public String getPhotourl() {
        return photourl;
    }

    public String getUser() {
        return user;
    }

    public String getSpecial() {
        return special;
    }

    public int getResources() {
        return resources;
    }

    public String getCreatedate() {
        return createdate;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public int getIs_del() {
        return is_del;
    }
}