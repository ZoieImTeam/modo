package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/9
 */
public class WeChatPayModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String order= (String) params[0];
        HttpTask.getInstance().getWpayInfo(order,this);
    }
}
