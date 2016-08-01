package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.channel.ChannelItem;

/**
 * Created by chenjy on 2016/1/31.
 */
public class ChannelItemResponse extends BaseResponse {
    private ChannelItem data;

    public ChannelItem getData() {
        return data;
    }

    public void setData(ChannelItem data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ChannelItemResponse{" +
                "data=" + data +
                '}';
    }
}
