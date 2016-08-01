package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.constants.HttpConstanst;
import com.binvshe.binvshe.http.base.HttpTask;

/**
 * 获取频道下的所有专题Model
 * Created by Administrator on 2016/3/12.
 */
public class GetChannelMoreModel extends BaseModel{
    @Override
    protected void onLoad(Object... params) throws Exception {
        String id = (String) params[0];
        String typeId = (String) params[1];
        String pageNo = (String) params[2];
        String pageSize = HttpConstanst.PAGE_SIZE_DEFAULT +"";
        HttpTask.getInstance().getChannelMore(this, id, typeId, pageNo, pageSize);
    }
}
