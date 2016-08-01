package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.constants.HttpConstanst;
import com.binvshe.binvshe.http.base.HttpTask;

/**
 * 获取专题详情
 */
public class GetCommentModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        if (params.length == 1) {
            String id = (String) params[0];
            HttpTask.getInstance().getComment(id, null, null, this);
        } else {
            String id = (String) params[0];
            String pageNo = (String) params[1];
            HttpTask.getInstance().getComment(id, HttpConstanst.PAGE_SIZE_DEFAULT + "", pageNo, this);
        }
    }
}
