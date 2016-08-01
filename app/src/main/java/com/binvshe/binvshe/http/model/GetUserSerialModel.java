package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.constants.HttpConstanst;
import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Administrator on 2016/3/13.
 */
public class GetUserSerialModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {

        String id = AccountManager.getInstance().getUserLogin().getUser().getId()+"";
        String typeId = (String) params[0];
        String pageNo = (String) params[1];
        String pageSize = HttpConstanst.PAGE_SIZE_DEFAULT + "";

        HttpTask.getInstance().getUserSerial(this, id, typeId, pageNo, pageSize);
    }
}
