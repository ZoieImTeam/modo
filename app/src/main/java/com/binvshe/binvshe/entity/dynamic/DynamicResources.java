package com.binvshe.binvshe.entity.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/17.
 */
public class DynamicResources {
    private int resourcesid;
    private int type;
    private String typename;
    private int user;
    private String createdate;
    private String updatedate;
    private String intro;
    private String text;

    private ArrayList<Photo> photos;

    private List<Reply> replys;

    public void setResourcesid(int resourcesid) {
        this.resourcesid = resourcesid;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public void setReplys(List<Reply> replys) {
        this.replys = replys;
    }

    public int getResourcesid() {
        return resourcesid;
    }

    public int getType() {
        return type;
    }

    public String getTypename() {
        return typename;
    }

    public int getUser() {
        return user;
    }

    public String getCreatedate() {
        return createdate;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public String getIntro() {
        return intro;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }



    public List<Reply> getReplys() {
        return replys;
    }
}