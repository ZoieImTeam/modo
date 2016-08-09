package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/9
 */
public class CancelOrderModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String order_id= (String) params[0];
        HttpTask.getInstance().getCancelOrder(order_id,this);
    }
}
