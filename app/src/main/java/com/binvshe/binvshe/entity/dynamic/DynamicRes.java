package com.binvshe.binvshe.entity.dynamic;

import com.binvshe.binvshe.http.response.BasePageResponse;

import java.util.List;

/**
 * Created by Administrator on 2016/2/17.
 */
public class DynamicRes extends BasePageResponse{

    private List<DynamicResources> datas;

    public void setDatas(List<DynamicResources> datas) {
        this.datas = datas;
    }

    public List<DynamicResources> getDatas() {
        return datas;
    }


}