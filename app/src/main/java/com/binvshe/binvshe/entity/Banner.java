package com.binvshe.binvshe.entity;

/**
 * Created by Administrator on 2016/2/16.
 */
public class Banner {

    private int id;
    private String name;
    private int status;
    private String photos;
    private String flogs;
    private String linke;
    private String descr;



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public void setFlogs(String flogs) {
        this.flogs = flogs;
    }

    public void setLinke(String linke) {
        this.linke = linke;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public String getPhotos() {
        return photos;
    }

    public String getFlogs() {
        return flogs;
    }

    public String getLinke() {
        return linke;
    }

    public String getDescr() {
        return descr;
    }
}
