package com.binvshe.binvshe.http.response;


import com.binvshe.binvshe.entity.ActivityList.TicketTypeEntity;

import java.util.ArrayList;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/7
 */
public class GetSelectTicketResponse extends BaseResponse {
    public ArrayList<TicketTypeEntity> getData() {
        return data;
    }

    public void setData(ArrayList<TicketTypeEntity> data) {
        this.data = data;
    }

    ArrayList<TicketTypeEntity> data;

}
