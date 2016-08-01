package com.binvshe.binvshe.entity.GetTicketList;

import java.util.ArrayList;
import java.util.List;

import com.binvshe.binvshe.http.response.BasePageResponse;
import com.google.gson.annotations.Expose;

public class GetTicketList extends BasePageResponse{

    @Expose
    private List<TicketData> datas = new ArrayList<TicketData>();

    /**
     * 
     * @return
     *     The datas
     */
    public List<TicketData> getDatas() {
        return datas;
    }

    /**
     * 
     * @param datas
     *     The datas
     */
    public void setDatas(List<TicketData> datas) {
        this.datas = datas;
    }


}
