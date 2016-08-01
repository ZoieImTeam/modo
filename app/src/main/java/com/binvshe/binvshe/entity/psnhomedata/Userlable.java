package com.binvshe.binvshe.entity.psnhomedata;

import com.google.gson.annotations.Expose;

public class Userlable {

    @Expose
    private Integer lableid;
    @Expose
    private String name;
    @Expose
    private String photos;
    @Expose
    private String createdate;

    /**
     * 
     * @return
     *     The lableid
     */
    public Integer getLableid() {
        return lableid;
    }

    /**
     * 
     * @param lableid
     *     The lableid
     */
    public void setLableid(Integer lableid) {
        this.lableid = lableid;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The photos
     */
    public String getPhotos() {
        return photos;
    }

    /**
     * 
     * @param photos
     *     The photos
     */
    public void setPhotos(String photos) {
        this.photos = photos;
    }

    /**
     * 
     * @return
     *     The createdate
     */
    public String getCreatedate() {
        return createdate;
    }

    /**
     * 
     * @param createdate
     *     The createdate
     */
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

}
