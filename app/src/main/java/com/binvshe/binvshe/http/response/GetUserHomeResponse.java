package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.UserLogin.UserLogin;
import com.binvshe.binvshe.http.response.data.GetUserHomeData;

/**
 * 获取个人主页所有专题Response
 */
public class GetUserHomeResponse extends BaseResponse {

    private GetUserHomeData data;

    public GetUserHomeData getData() {
        return data;
    }

    public void setData(GetUserHomeData data) {
        this.data = data;
    }
}
