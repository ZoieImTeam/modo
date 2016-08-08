package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/8
 */
public class AliPayInfoModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String order_no= (String) params[0];
        HttpTask.getInstance().getAliPayInfo(order_no,this);
    }
}
