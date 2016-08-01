package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Cainer on 2016/3/28.
 */
public class GetAttentionListModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String id= String.valueOf(params[0]);
        String pageNo=String.valueOf(params[1]);
        HttpTask.getInstance().getAttenMor(this,id,pageNo);
    }
}
