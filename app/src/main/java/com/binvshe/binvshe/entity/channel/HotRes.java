package com.binvshe.binvshe.entity.channel;

import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public class HotRes {

    private List<HotCatetory> list;
    private List<CatetoryItem> type;

    public void setList(List<HotCatetory> list) {
        this.list = list;
    }

    public void setType(List<CatetoryItem> type) {
        this.type = type;
    }

    public List<HotCatetory> getList() {
        return list;
    }

    public List<CatetoryItem> getType() {
        return type;
    }
}
