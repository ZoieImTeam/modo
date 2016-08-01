package com.binvshe.binvshe.http.response.data;

import com.binvshe.binvshe.entity.subject.SubjectSerialEntity;
import com.binvshe.binvshe.http.response.BasePageResponse;

import java.util.ArrayList;

/**
 * 获取用户所有连载Data
 */
public class GetUserSerialData extends BasePageResponse {

    private ArrayList<SubjectSerialEntity> datas;

    public ArrayList<SubjectSerialEntity> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<SubjectSerialEntity> datas) {
        this.datas = datas;
    }
}
