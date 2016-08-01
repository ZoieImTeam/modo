package com.binvshe.binvshe.entity.psnhomedata;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class GetUserdata {

    @Expose
    private List<TypeData> datas = new ArrayList<TypeData>();
    @Expose
    private Userinfo userinfo;
    @Expose
    private List<Userlable> userlable = new ArrayList<Userlable>();

    /**
     * 
     * @return
     *     The datas
     */
    public List<TypeData> getDatas() {
        return datas;
    }

    /**
     * 
     * @param datas
     *     The datas
     */
    public void setDatas(List<TypeData> datas) {
        this.datas = datas;
    }

    /**
     * 
     * @return
     *     The userinfo
     */
    public Userinfo getUserinfo() {
        return userinfo;
    }

    /**
     * 
     * @param userinfo
     *     The userinfo
     */
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    /**
     * 
     * @return
     *     The userlable
     */
    public List<Userlable> getUserlable() {
        return userlable;
    }

    /**
     * 
     * @param userlable
     *     The userlable
     */
    public void setUserlable(List<Userlable> userlable) {
        this.userlable = userlable;
    }

}
