package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.helper.AccountManager;
import com.binvshe.binvshe.http.base.HttpTask;

/**
 * Created by Cainer on 2016/4/21.
 */
public class AddSerialModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String title= (String) params[0];
        String user= AccountManager.getInstance().getUserLogin().getUser().getId()+"";
        String typeId= (String) params[1];
        HttpTask.getInstance().addSerial(title,user,typeId,this);
    }
}
