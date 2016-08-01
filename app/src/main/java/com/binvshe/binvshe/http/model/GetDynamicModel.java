package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.constants.HttpConstanst;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.base.HttpTask;

/**
 * 获取专题详情
 */
public class GetDynamicModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String uid= AccountManager.getInstance().getUserLogin().getUser().getId()+"";
        if(params.length == 1){
            String id = (String) params[0];
            HttpTask.getInstance().getDynamic(id,uid, null, null, this);
        }else{
            String id = (String) params[0];
            String pageNo = (String) params[1];
            HttpTask.getInstance().getDynamic(id,uid,HttpConstanst.PAGE_SIZE_DEFAULT + "", pageNo, this);
        }


    }
}
