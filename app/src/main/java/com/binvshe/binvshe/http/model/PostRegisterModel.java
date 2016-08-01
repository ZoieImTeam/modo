package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * 手机号注册Model
 * Created by chenjy on 2016/2/14.
 */
public class PostRegisterModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String user = (String) params[0];
        String pwd = (String) params[1];
        HttpTask.getInstance().postRegister(this, user, pwd);
    }
}
