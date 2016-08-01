package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.WechatPay;

/**
 * Created by chenjy on 2015/12/29.
 */
public class WechatPayResponse extends BaseResponse {
        private WechatPay data;

    @Override
    public String toString() {
        return "WechatPayResponse{" +
                "data=" + data +
                '}';
    }

    public WechatPay getData() {
        return data;
    }

    public void setData(WechatPay data) {
        this.data = data;
    }
}
