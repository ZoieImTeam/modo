package com.binvshe.binvshe.entity;

import com.binvshe.binvshe.entity.attention.AttentionDatas;
import com.binvshe.binvshe.http.response.BasePageResponse;

import java.util.List;

/**
 * Created by Cainer on 2016/3/28.
 */
public class GetAttentionListData extends BasePageResponse{

        private List<AttentionDatas> datas;


        public List<AttentionDatas> getDatas() {
            return datas;
        }

        public void setDatas(List<AttentionDatas> datas) {
            this.datas = datas;
        }


}
