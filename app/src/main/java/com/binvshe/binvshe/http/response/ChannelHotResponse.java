package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.channel.HotRes;

/**
 * Created by chenjy on 2016/1/31.
 */
public class ChannelHotResponse extends BaseResponse {
    private HotRes data;

    public HotRes getData() {
        return data;
    }

    public void setData(HotRes data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ChannelHotResponse{" +
                "data=" + data +
                '}';
    }
}
