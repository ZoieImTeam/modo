package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2016/1/29.
 */
public class PostAliPayInfoModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String userid = (String) params[0];
        String money = (String) params[1];
        String type = (String) params[2];
        String activityid = (String) params[3];
        String discount;
        if(!params[4].equals("")) {
            discount = (String) params[4];
        }else  discount=0+"";
        HttpTask.getInstance().postAliPayInfo(this, userid, money, type, activityid,discount);
    }
}
