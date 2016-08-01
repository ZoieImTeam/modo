package com.binvshe.binvshe.http.base;

import com.binvshe.binvshe.http.model.BaseModel;

/**
 * Created by chenjy on 2016/2/19.
 */
public class GetPsnhomeModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String ids = String.valueOf(params[0]);
        HttpTask.getInstance().getPsnhomeUserdata(this, ids);
    }
}
