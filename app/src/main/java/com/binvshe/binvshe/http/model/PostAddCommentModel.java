package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.http.base.HttpTask;

public class PostAddCommentModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String resources = (String) params[0];
        String content = (String) params[1];
        String userb_id = (String) params[2];
        String usera_id = SpUtils.getUserID();
        HttpTask.getInstance().postAddComment(usera_id,userb_id, resources, content, this);
    }
}
