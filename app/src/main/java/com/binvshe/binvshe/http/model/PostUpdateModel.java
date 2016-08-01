package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2016/2/16.
 */
public class PostUpdateModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String user = (String) params[0];
        String pwd = (String) params[1];
        HttpTask.getInstance().postUpdatePwd(this, user, pwd);
    }
}
