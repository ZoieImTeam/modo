package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * 添加关注用户Model
 */
public class CancelAttentionUserModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {

        String usera = (String) params[0];
        String userb = (String) params[1];

        HttpTask.getInstance().cancelAttentionUser(this, usera, userb);


    }
}
