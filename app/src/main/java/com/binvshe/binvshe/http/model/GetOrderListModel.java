package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/9
 */
public class GetOrderListModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String userID = AccountManager.getInstance().getUserLogin().getUser().getId()+"";
        HttpTask.getInstance().getOrderList(userID,this);
    }
}
