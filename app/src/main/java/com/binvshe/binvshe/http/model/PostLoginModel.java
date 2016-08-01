package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2015/12/1.
 */
public class PostLoginModel extends BaseModel{
    @Override
    protected void onLoad(Object... params) throws Exception {
        String user = String.valueOf(params[0]);
        String pwd = String.valueOf(params[1]);
        HttpTask.getInstance().postLoginInfo(this, user, pwd);
    }
}
