package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Cainer on 2016/4/21.
 */
public class GetVersionModel extends BaseModel{
    @Override
    protected void onLoad(Object... params) throws Exception {
        String type=1+"";
        HttpTask.getInstance().getVersion(type,this);
    }
}
