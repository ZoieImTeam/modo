package com.binvshe.binvshe.entity.dynamic;

/**
 * Created by Administrator on 2016/2/17.
 */
public class Reply {
    private int replysid;
    private int activity_id;
    private int usera_id;
    private String usera_name;
    private int userb_id;
    private String userb_name;
    private String photos;
    private int is_del;

    private String createdate;
    private String content;
    private int rid;
    private int lou;
    private String usera_img;
    private String userb_img;
    private int resources;

    public void setReplysid(int replysid) {
        this.replysid = replysid;
    }

    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }

    public void setUsera_id(int usera_id) {
        this.usera_id = usera_id;
    }

    public void setUsera_name(String usera_name) {
        this.usera_name = usera_name;
    }

    public void setUserb_id(int userb_id) {
        this.userb_id = userb_id;
    }

    public void setUserb_name(String userb_name) {
        this.userb_name = userb_name;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public void setLou(int lou) {
        this.lou = lou;
    }

    public void setUsera_img(String usera_img) {
        this.usera_img = usera_img;
    }

    public void setUserb_img(String userb_img) {
        this.userb_img = userb_img;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public int getReplysid() {
        return replysid;
    }

    public int getActivity_id() {
        return activity_id;
    }

    public int getUsera_id() {
        return usera_id;
    }

    public String getUsera_name() {
        return usera_name;
    }

    public int getUserb_id() {
        return userb_id;
    }

    public String getUserb_name() {
        return userb_name;
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

    public String getContent() {
        return content;
    }

    public int getRid() {
        return rid;
    }

    public int getLou() {
        return lou;
    }

    public String getUsera_img() {
        return usera_img;
    }

    public String getUserb_img() {
        return userb_img;
    }

    public int getResources() {
        return resources;
    }

}