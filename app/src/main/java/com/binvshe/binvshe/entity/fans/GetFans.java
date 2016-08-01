package com.binvshe.binvshe.entity.fans;

import com.binvshe.binvshe.http.response.BasePageResponse;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class GetFans extends BasePageResponse{

    private ArrayList<FanData> datas = new ArrayList<FanData>();

    /**
     * 
     * @return
     *     The datas
     */
    public ArrayList<FanData> getDatas() {
        return datas;
    }

    /**
     * 
     * @param datas
     *     The datas
     */
    public void setDatas(ArrayList<FanData> datas) {
        this.datas = datas;
    }

}
