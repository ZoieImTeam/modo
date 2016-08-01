package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.HomeRec.HomeRec;

import java.util.List;

/**
 * Created by chenjy on 2016/1/31.
 */
public class HomeRecResponse extends BaseResponse {
    private HomeRec data;

    public HomeRec getData() {
        return data;
    }

    public void setData(HomeRec data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HomeRecResponse{" +
                "data=" + data +
                '}';
    }
}
