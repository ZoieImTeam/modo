package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

public class PostFreeBuyModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String useraid = (String) params[0];
        String activityid = (String) params[1];
        String purpose = (String) params[2];
        HttpTask.getInstance().postFreeBuy(useraid, activityid, purpose,this);
    }
}
