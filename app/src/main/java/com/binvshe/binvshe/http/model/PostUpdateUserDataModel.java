package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * 修改个人资料Model
 */
public class PostUpdateUserDataModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String ids = (String) params[0];
        String head = (String) params[1];
        String name = (String) params[2];
        String sex = (String) params[3];
        String sign = (String) params[4];
        HttpTask.getInstance().postUpdateUserData(this, ids, head, name, sex, sign);
    }
}
