package com.binvshe.binvshe.entity.channel;

import com.binvshe.binvshe.entity.Dynamic;

import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public class HotCatetory {

    private CatetoryItem type;

    private List<Dynamic> datas;

    public void setType(CatetoryItem type) {
        this.type = type;
    }

    public void setDatas(List<Dynamic> datas) {
        this.datas = datas;
    }

    public CatetoryItem getType() {
        return type;
    }

    public List<Dynamic> getDatas() {
        return datas;
    }
}