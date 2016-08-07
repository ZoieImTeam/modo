package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/7
 */
public class SelectDateFirnModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String activityId= (String) params[0];
        HttpTask.getInstance().getDateFirn(activityId,this);
    }
}
