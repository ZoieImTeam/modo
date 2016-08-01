package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.fans.GetFans;

/**
 * Created by chenjy on 2016/2/17.
 */
public class GetFansResponse extends BaseResponse {
    private GetFans data;

    public GetFans getData() {
        return data;
    }

    public void setData(GetFans data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetFansResponse{" +
                "data=" + data +
                '}';
    }
}
