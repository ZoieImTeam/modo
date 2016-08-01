package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.GetVersionData;

/**
 * Created by Cainer on 2016/4/21.
 */
public class GetVersionResponse extends BaseResponse {
    public GetVersionData getData() {
        return data;
    }

    public void setData(GetVersionData data) {
        this.data = data;
    }

    private GetVersionData data;
}
