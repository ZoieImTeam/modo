package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.ActivityList.SelectDateFirnData;
import com.binvshe.binvshe.http.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/7
 */
public class GetSelectDateFirnResponse extends BaseResponse {


    public ArrayList<SelectDateFirnData> getData() {
        return data;
    }

    public void setData(ArrayList<SelectDateFirnData> data) {
        this.data = data;
    }

    private ArrayList<SelectDateFirnData> data;
}
