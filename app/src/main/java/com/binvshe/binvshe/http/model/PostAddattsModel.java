package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2016/2/20.
 */
public class PostAddattsModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String usera = (String) params[0];
        String userb = (String) params[1];
        HttpTask.getInstance().postAddatts(this, usera, userb);
    }
}
