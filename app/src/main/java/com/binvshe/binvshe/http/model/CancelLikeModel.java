package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * 给专题点赞Model
 * Created by Administrator on 2016/3/13.
 */
public class CancelLikeModel extends BaseModel{

    public static final String ADD_LIKE_FALSE = "0";
    public static final String ADD_LIKE_TRUE = "1";

    @Override
    protected void onLoad(Object... params) throws Exception {

        String specialId = (String) params[0];
        String id = (String) params[1];

        HttpTask.getInstance().cancelLike(this, specialId, id);
    }
}
