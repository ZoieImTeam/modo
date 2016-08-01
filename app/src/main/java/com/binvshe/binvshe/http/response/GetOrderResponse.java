package com.binvshe.binvshe.http.response;


import com.binvshe.binvshe.entity.GetOrder;

/**
 * Created by chenjy on 2015/12/25.
 */
public class GetOrderResponse extends BaseResponse {
    private GetOrder data;

    @Override
    public String toString() {
        return "GetOrderResponse{" +
                "data=" + data +
                '}';
    }

    public GetOrder getData() {
        return data;
    }

    public void setData(GetOrder data) {
        this.data = data;
    }
}
