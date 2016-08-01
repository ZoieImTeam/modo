package com.binvshe.binvshe.entity.psnhomedata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TypeData {

    @Expose
    private Integer id;
    @Expose
    private String name;
    @SerializedName("is_del")
    @Expose
    private Integer isDel;
    @Expose
    private java.util.List<List> list = new ArrayList<List>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
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
     *     The isDel
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 
     * @param isDel
     *     The is_del
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 
     * @return
     *     The list
     */
    public java.util.List<List> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(java.util.List<List> list) {
        this.list = list;
    }

}
