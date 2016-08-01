package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.http.response.data.GetUserSerialData;

/**
 * 获取用户所有连载Response
 */
public class GetUserSerialResponse extends BaseResponse {

    private GetUserSerialData data;

    public GetUserSerialData getData() {
        return data;
    }

    public void setData(GetUserSerialData data) {
        this.data = data;
    }
}
