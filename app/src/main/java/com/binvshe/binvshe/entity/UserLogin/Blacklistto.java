package com.binvshe.binvshe.entity.UserLogin;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by chenjy on 2015/12/3.
 */
@DatabaseTable(tableName = "tab_blacklistto")
public class Blacklistto implements Serializable{
    private static final long serialVersionUID = 1L;
    @DatabaseField(columnName = "id")
    private int id;
    @DatabaseField(columnName = "usera")
    private int usera;

    @Override
    public String toString() {
        return "Blacklistto{" +
                "id=" + id +
                ", usera=" + usera +
                ", userb=" + userb +
                '}';
    }

    public int getUserb() {
        return userb;
    }

    public void setUserb(int userb) {
        this.userb = userb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsera() {
        return usera;
    }

    public void setUsera(int usera) {
        this.usera = usera;
    }

    @DatabaseField(columnName = "userb")
    private int userb;

}
