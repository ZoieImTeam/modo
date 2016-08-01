package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.ActivityList.GetActivityList;

/**
 * Created by chenjy on 2016/1/31.
 */
public class GetActivityListResponse extends BaseResponse {
    private GetActivityList data;

    public GetActivityList getData() {
        return data;
    }

    public void setData(GetActivityList data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetActivityListResponse{" +
                "data=" + data +
                '}';
    }
}
