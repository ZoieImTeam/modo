package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Cainer on 2016/4/28.
 */
public class GetMyLikeModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String userid= AccountManager.getInstance().getUserLogin().getUser().getId()+"";
        String pageNo= (String) params[0];
        HttpTask.getInstance().myLikeSpecial(userid,pageNo,this);
    }
}
