package com.binvshe.binvshe.entity.UserLogin;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tab_attention")
public class Attention {

    @DatabaseField(columnName = "aid")
    @Expose
    private Integer id;
    @DatabaseField(columnName = "name")
    @Expose
    private String name;
    @DatabaseField(columnName = "head")
    @Expose
    private String head;
    @DatabaseField(columnName = "sign")
    @Expose
    private String sign;
    @DatabaseField(columnName = "auth")
    @Expose
    private Integer auth;

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
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
     *     The head
     */
    public String getHead() {
        return head;
    }

    /**
     * 
     * @param head
     *     The head
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * 
     * @return
     *     The sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * 
     * @param sign
     *     The sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "Attention{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", head='" + head + '\'' +
                ", sign='" + sign + '\'' +
                ", auth=" + auth +
                '}';
    }
}
