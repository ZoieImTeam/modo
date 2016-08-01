package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.http.response.data.UpdateHeadData;

/**
 * Created by Administrator on 2016/3/18.
 */
public class UpdateHeadResponse extends BaseResponse{

    private UpdateHeadData data;

    public UpdateHeadData getData() {
        return data;
    }

    public void setData(UpdateHeadData data) {
        this.data = data;
    }
}
