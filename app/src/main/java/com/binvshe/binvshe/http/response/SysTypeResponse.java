package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.SysTypeEntitiy;

import java.util.List;

/**
 * Created by chenjy on 2016/1/31.
 */
public class SysTypeResponse extends BaseResponse {
    private List<SysTypeEntitiy> data;

    public List<SysTypeEntitiy> getData() {
        return data;
    }

    public void setData(List<SysTypeEntitiy> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SysTypeResponse{" +
                "data=" + data +
                '}';
    }
}
