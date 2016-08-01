package com.binvshe.binvshe.http.response.data;

import com.binvshe.binvshe.entity.subject.SubjectEntity;
import com.binvshe.binvshe.http.response.BasePageResponse;

import java.util.ArrayList;

/**
 * 获取个人主页所有专题Response
 */
public class GetUserHomeData extends BasePageResponse{

    private ArrayList<SubjectEntity> datas;

    public ArrayList<SubjectEntity> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<SubjectEntity> datas) {
        this.datas = datas;
    }


}
