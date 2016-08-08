package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/8
 */
public class CreateOrderModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String activityId= (String) params[0];
        String num= (String) params[1];
        String useraid= (String) params[2];
        String productId= (String) params[3];
        HttpTask.getInstance().postProOrder(activityId,num,useraid,productId,this);
    }
}
