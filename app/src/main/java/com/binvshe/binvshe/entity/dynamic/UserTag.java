package com.binvshe.binvshe.entity.dynamic;

/**
 * Created by Administrator on 2016/2/17.
 */
public class UserTag {
    private int lableid;
    private String name;
    private String photos;
    private String createdate;

    public void setLableid(int lableid) {
        this.lableid = lableid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getLableid() {
        return lableid;
    }

    public String getName() {
        return name;
    }

    public String getPhotos() {
        return photos;
    }

    public String getCreatedate() {
        return createdate;
    }
}