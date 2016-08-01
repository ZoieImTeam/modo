package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2016/1/31.
 */
public class GetActivityListModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String pageNo = (String) params[0];
        HttpTask.getInstance().getActivityList(this, pageNo);
    }
}
