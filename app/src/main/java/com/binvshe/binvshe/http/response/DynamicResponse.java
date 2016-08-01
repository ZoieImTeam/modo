package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.dynamic.DynamicDetail;

public class DynamicResponse extends BaseResponse {
    private DynamicDetail data;

    public DynamicDetail getData() {
        return data;
    }

    public void setData(DynamicDetail data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DynamicRecResponse{" +
                "data=" + data +
                '}';
    }
}
