package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2016/2/14.
 */
public class GetOrderModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String orderNo = (String) params[0];
        String type = (String) params[1];
        HttpTask.getInstance().getOrder(this, orderNo, type);
    }
}
