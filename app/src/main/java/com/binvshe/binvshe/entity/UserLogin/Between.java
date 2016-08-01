package com.binvshe.binvshe.entity.UserLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tab_between")
public class Between {
    @DatabaseField(columnName = "id")
    @Expose
    private Integer id;
    @DatabaseField(columnName = "usera")
    @Expose
    private Integer usera;
    @DatabaseField(columnName = "userb")
    @Expose
    private Integer userb;
    @DatabaseField(columnName = "spectypeId")
    @SerializedName("spectype_id")
    @Expose
    private Integer spectypeId;
    @DatabaseField(columnName = "status")
    @Expose
    private Integer status;

    @Override
    public String toString() {
        return "Between{" +
                "id=" + id +
                ", usera=" + usera +
                ", userb=" + userb +
                ", spectypeId=" + spectypeId +
                ", status=" + status +
                '}';
    }

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
     *     The usera
     */
    public Integer getUsera() {
        return usera;
    }

    /**
     * 
     * @param usera
     *     The usera
     */
    public void setUsera(Integer usera) {
        this.usera = usera;
    }

    /**
     * 
     * @return
     *     The userb
     */
    public Integer getUserb() {
        return userb;
    }

    /**
     * 
     * @param userb
     *     The userb
     */
    public void setUserb(Integer userb) {
        this.userb = userb;
    }

    /**
     * 
     * @return
     *     The spectypeId
     */
    public Integer getSpectypeId() {
        return spectypeId;
    }

    /**
     * 
     * @param spectypeId
     *     The spectype_id
     */
    public void setSpectypeId(Integer spectypeId) {
        this.spectypeId = spectypeId;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}
