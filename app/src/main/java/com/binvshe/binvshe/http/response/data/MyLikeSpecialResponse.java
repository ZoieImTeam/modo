package com.binvshe.binvshe.http.response.data;

import com.binvshe.binvshe.entity.MyLikeSpecialEntity;
import com.binvshe.binvshe.http.response.BaseResponse;

/**
 * Created by Cainer on 2016/4/21.
 */
public class MyLikeSpecialResponse extends BaseResponse{
    public MyLikeSpecialEntity getData() {
        return data;
    }

    public void setData(MyLikeSpecialEntity data) {
        this.data = data;
    }

    private MyLikeSpecialEntity data;
}
