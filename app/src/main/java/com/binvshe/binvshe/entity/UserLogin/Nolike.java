package com.binvshe.binvshe.entity.UserLogin;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tab_nolike")
public class Nolike {
    @DatabaseField(columnName = "id")
    @Expose
    private Integer id;
    @DatabaseField(columnName = "user")
    @Expose
    private Integer user;
    @DatabaseField(columnName = "dynmic")
    @Expose
    private Integer dynmic;

    @Override
    public String toString() {
        return "Nolike{" +
                "id=" + id +
                ", user=" + user +
                ", dynmic=" + dynmic +
                '}';
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The user
     */
    public Integer getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * @return The dynmic
     */
    public Integer getDynmic() {
        return dynmic;
    }

    /**
     * @param dynmic The dynmic
     */
    public void setDynmic(Integer dynmic) {
        this.dynmic = dynmic;
    }

}
