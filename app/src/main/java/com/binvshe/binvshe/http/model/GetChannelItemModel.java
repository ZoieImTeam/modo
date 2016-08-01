package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2016/2/17.
 */
public class GetChannelItemModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String ids = String.valueOf(params[0]);
        HttpTask.getInstance().getChannelItem(ids,this);
    }
}
