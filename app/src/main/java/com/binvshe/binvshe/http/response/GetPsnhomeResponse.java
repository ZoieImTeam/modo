package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.psnhomedata.GetUserdata;

/**
 * Created by chenjy on 2016/2/19.
 */
public class GetPsnhomeResponse extends BaseResponse {
    private GetUserdata data;

    public GetUserdata getData() {
        return data;
    }

    public void setData(GetUserdata data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetPsnhomeResponse{" +
                "data=" + data +
                '}';
    }
}
