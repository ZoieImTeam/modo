package com.binvshe.binvshe.entity.subject;

import java.util.ArrayList;

/**
 * 系统分类
 */
public class SysTypeEntitiy {
    private String id;
    private String name;
    private String pid;
    private String photos;
    private String is_del;
    private String createdate;
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
}
