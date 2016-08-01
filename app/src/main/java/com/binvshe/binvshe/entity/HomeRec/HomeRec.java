package com.binvshe.binvshe.entity.HomeRec;

import com.binvshe.binvshe.entity.Banner;
import com.binvshe.binvshe.entity.subject.SubjectEntity;
import com.binvshe.binvshe.entity.subject.SysTypeEntitiy;

import java.util.ArrayList;

/**
 * 首页推荐数据结构Entitiy
 */
public class HomeRec {
    private ArrayList<Banner> banner;
    private ArrayList<SysTypeEntitiy> datas;
    private ArrayList<SubjectEntity> hot;

    public ArrayList<Banner> getBanner() {
        return banner;
    }

    public void setBanner(ArrayList<Banner> banner) {
        this.banner = banner;
    }

    public ArrayList<SysTypeEntitiy> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<SysTypeEntitiy> datas) {
        this.datas = datas;
    }

    public ArrayList<SubjectEntity> getHot() {
        return hot;
    }

    public void setHot(ArrayList<SubjectEntity> hot) {
        this.hot = hot;
    }
}
