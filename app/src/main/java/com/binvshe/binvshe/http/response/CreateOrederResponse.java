package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.ActivityList.CreateOrderEntity;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/8
 */
public class CreateOrederResponse extends BaseResponse {
    public CreateOrderEntity getData() {
        return data;
    }

    public void setData(CreateOrderEntity data) {
        this.data = data;
    }

    private CreateOrderEntity data;
}
