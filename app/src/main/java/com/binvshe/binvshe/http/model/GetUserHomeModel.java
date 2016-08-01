package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.constants.HttpConstanst;
import com.binvshe.binvshe.http.base.HttpTask;

/**
 * 获取个人主页所有专题Model
 * Created by Administrator on 2016/3/12.
 */
public class GetUserHomeModel extends BaseModel{
    @Override
    protected void onLoad(Object... params) throws Exception {
        String id = (String) params[0];
        String pageNo = (String) params[1];
        String pageSize = HttpConstanst.PAGE_SIZE_DEFAULT +"";
        HttpTask.getInstance().getUserHome(this, id, pageNo, pageSize);
    }
}
