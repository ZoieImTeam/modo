package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2016/1/31.
 */
public class GetHomeRecModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String id = (String) params[0];
        HttpTask.getInstance().getHomeRec(this, id);
    }
}
