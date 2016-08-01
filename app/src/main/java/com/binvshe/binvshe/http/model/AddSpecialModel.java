package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

import java.io.File;
import java.util.ArrayList;

/**
 *  发布Model
 */
public class AddSpecialModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        String ids = (String) params[0];
        String name = (String) params[1];
        String userId = (String) params[2];
        String type = (String) params[3];
        String sysType = (String) params[4];
        String desc = (String) params[5];
        String original = (String) params[6];
        String roleInfo = (String) params[7];
        String camerman = (String) params[8];
        String serial = (String) params[9];
        ArrayList<File> fileList = (ArrayList<File>) params[10];
        String text= (String) params[11];
        String showtype= (String) params[12];
        String serialid= (String) params[13];

        HttpTask.getInstance().addSpecial(this, ids, name, userId, type, sysType, desc, original, roleInfo, camerman, serial, fileList,text,showtype,serialid);

    }
}
