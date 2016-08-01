package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.http.response.data.GetUserCenterData;

/**
 * Created by Administrator on 2016/3/18.
 */
public class GetUserCenterResponse extends BaseResponse{

    private GetUserCenterData data;

    public GetUserCenterData getData() {
        return data;
    }

    public void setData(GetUserCenterData data) {
        this.data = data;
    }
}
