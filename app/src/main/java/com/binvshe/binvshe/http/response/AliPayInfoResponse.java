package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.AliPayInfo;

/**
 * Created by chenjy on 2015/12/23.
 */
public class AliPayInfoResponse extends BaseResponse {
    private AliPayInfo data;

    @Override
    public String toString() {
        return "PostRechargeResponse{" +
                "data=" + data +
                '}';
    }

    public AliPayInfo getData() {
        return data;
    }

    public void setData(AliPayInfo data) {
        this.data = data;
    }
}
