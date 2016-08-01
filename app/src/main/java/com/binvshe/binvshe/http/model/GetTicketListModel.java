package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by chenjy on 2016/2/15.
 */
public class GetTicketListModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String ids = (String) params[0];
        String pageNo = String.valueOf(params[1]);
        HttpTask.getInstance().getTicketList(this, ids, pageNo);
    }
}
