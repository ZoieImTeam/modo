package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/7
 */
public class SelectTickMsgModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String activityId= (String) params[0];
        String lifecycle= (String) params[1];
        String game= (String) params[2];
        HttpTask.getInstance().getTickType(activityId,lifecycle,game,this);
    }
}
