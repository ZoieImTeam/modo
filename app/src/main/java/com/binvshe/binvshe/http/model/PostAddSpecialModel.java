package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by chenjy on 2016/1/29.
 */
public class PostAddSpecialModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String userid = (String) params[0];
        String systypeid = (String) params[1];
        String sysdesc = (String) params[2];  //总简介
        String name = (String) params[3];
        String desc = (String) params[4];  //每单个简介
        String text = (String) params[5];
        String type = (String) params[6];
        String ids = (String) params[7];
        ArrayList<File> files = (ArrayList<File>) params[8];
        HttpTask.getInstance().postAddSpecial(userid, systypeid, sysdesc, name,desc,text,type,ids,files, this);
    }
}
