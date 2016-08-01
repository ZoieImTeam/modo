package com.binvshe.binvshe.http.model;

import android.widget.Toast;

import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.http.base.HttpTask;

import org.srr.dev.base.BaseApplication;
/**
 * 添加关注用户Model
 */
public class AddAttentionUserModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {

        String usera = (String) params[0];
        String userb = (String) params[1];

        if(usera.equals(userb))
        {
            Toast.makeText(BaseApp.mContext,"不能关注自己",Toast.LENGTH_SHORT).show();
        }
        else HttpTask.getInstance().addAttentionUser(this, usera, userb);


    }
}
