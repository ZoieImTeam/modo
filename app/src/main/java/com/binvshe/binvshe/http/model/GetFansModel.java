package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * 获取关注或粉丝列表
 */
public class GetFansModel extends BaseModel {

    /**
     * 关注
     */
    public static final String TYPE_ATTENTION = "0";
    /**
     * 粉丝
     */
    public static final String TYPE_FANS = "1";
    @Override
    protected void onLoad(Object... params) throws Exception {
        String ids = String.valueOf(params[0]);
        String type = String.valueOf(params[1]);
        String pageNo = String.valueOf(params[2]);
        HttpTask.getInstance().getFans(this, ids, type, pageNo);
    }
}
