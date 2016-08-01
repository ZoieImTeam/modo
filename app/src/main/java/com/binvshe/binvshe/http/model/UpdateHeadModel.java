package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

import java.io.File;

/**
 * 上传头像Model
 */
public class UpdateHeadModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        File file = (File) params[0];
        String id = (String) params[1];

        HttpTask.getInstance().updateHead(file, id, this);
    }

}
