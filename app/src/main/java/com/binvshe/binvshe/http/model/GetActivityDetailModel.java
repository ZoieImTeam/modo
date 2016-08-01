package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2016/1/31.
 */
public class GetActivityDetailModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String uid = (String) params[0];
        String ids = (String) params[1];
        HttpTask.getInstance().getActivityDetail(this, uid, ids);
    }
}
