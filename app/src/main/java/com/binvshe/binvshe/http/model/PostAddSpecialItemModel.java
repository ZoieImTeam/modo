package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by chenjy on 2016/1/29.
 */
public class PostAddSpecialItemModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String ids = (String) params[0]; //总资源id
        String intro = (String) params[1];  //每单个简介
        String text = (String) params[2];
        ArrayList<File> files = (ArrayList<File>) params[3];
        HttpTask.getInstance().postAddSpecialItem(ids,intro,text,files, this);
    }
}
