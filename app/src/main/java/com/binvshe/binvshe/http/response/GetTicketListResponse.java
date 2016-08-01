package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.GetTicketList.GetTicketList;

/**
 * Created by chenjy on 2016/2/15.
 */
public class GetTicketListResponse extends BaseResponse {
    private GetTicketList data;

    public GetTicketList getData() {
        return data;
    }

    public void setData(GetTicketList data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetTicketListResponse{" +
                "data=" + data +
                '}';
    }
}
