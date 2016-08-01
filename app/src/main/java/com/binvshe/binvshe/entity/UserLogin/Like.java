package com.binvshe.binvshe.entity.UserLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tab_like")
public class Like {
    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", dynamicId=" + dynamicId +
                ", createdate='" + createdate + '\'' +
                '}';
    }
    @DatabaseField(columnName = "id")
    @Expose
    private Integer id;
    @DatabaseField(columnName = "dynamicId")
    @SerializedName("dynamic_id")
    @Expose
    private Integer dynamicId;
    @DatabaseField(columnName = "createdate")
    @Expose
    private String createdate;

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
     *     The dynamicId
     */
    public Integer getDynamicId() {
        return dynamicId;
    }

    /**
     * 
     * @param dynamicId
     *     The dynamic_id
     */
    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
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
