package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.ActivityList.ActivityData;

/**
 * Created by chenjy on 2016/1/31.
 */
public class GetActivityDetailResponse extends BaseResponse {
    private ActivityData data;

    public ActivityData getData() {
        return data;
    }

    public void setData(ActivityData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetActivityDetailResponse{" +
                "data=" + data +
                '}';
    }
}
