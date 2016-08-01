package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * 获取个人中心（关注数，粉丝数,用户基本信息）Model
 * Created by Administrator on 2016/3/12.
 */
public class GetUserCenterModel extends BaseModel{
    @Override
    protected void onLoad(Object... params) throws Exception {
        if(params.length == 1){
            String id = (String) params[0];
            HttpTask.getInstance().getUserCenterData(this, id, null);
        }else{
            String id = (String) params[0];
            String myid = (String) params[1];
            HttpTask.getInstance().getUserCenterData(this, id, myid);
        }


    }
}
