package com.binvshe.binvshe.entity;

/**
 * Created by Cainer on 2016/4/21.
 */
public class GetVersionData {

    /**
     * id : 1
     * verNo : 1.0
     * contents : 测试版本android
     * linke : http://baidu.com
     */

    private int id;
    private String verNo;
    private String contents;
    private String linke;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVerNo() {
        return verNo;
    }

    public void setVerNo(String verNo) {
        this.verNo = verNo;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getLinke() {
        return linke;
    }

    public void setLinke(String linke) {
        this.linke = linke;
    }
}
