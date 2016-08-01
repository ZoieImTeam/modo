package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.GetAttentionListData;

/**
 * Created by Administrator on 2016/3/18.
 */
public class GetAttentionListResponse extends BaseResponse{

    private GetAttentionListData data;

    public GetAttentionListData getData() {
        return data;
    }

    public void setData(GetAttentionListData data) {
        this.data = data;
    }
}
