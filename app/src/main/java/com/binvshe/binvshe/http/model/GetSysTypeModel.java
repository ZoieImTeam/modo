package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2016/1/31.
 */
public class GetSysTypeModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String ids = (String) params[0];

        HttpTask.getInstance().getSysType(ids, this);
    }
}
