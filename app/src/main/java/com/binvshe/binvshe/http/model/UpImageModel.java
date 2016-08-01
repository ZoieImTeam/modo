package com.binvshe.binvshe.http.model;

import com.binvshe.binvshe.http.base.HttpTask;

import java.io.File;

/**
 * 上传单图Model
 */
public class UpImageModel extends BaseModel {
    @Override
    protected void onLoad(Object... params) throws Exception {
        File file = (File) params[0];
        String type = (String) params[1];
        String hight = (String) params[2];
        String width = (String) params[3];

        HttpTask.getInstance().UpImage(file, type, hight, width, this);
    }

}
