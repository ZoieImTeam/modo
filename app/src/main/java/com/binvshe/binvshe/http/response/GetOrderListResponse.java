package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.ActivityList.OrderListEntity;

import java.util.ArrayList;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/9
 */
public class GetOrderListResponse extends BaseResponse {

    private ArrayList<OrderListEntity> data;

    public ArrayList<OrderListEntity> getData() {
        return data;
    }

    public void setData(ArrayList<OrderListEntity> data) {
        this.data = data;
    }
}
