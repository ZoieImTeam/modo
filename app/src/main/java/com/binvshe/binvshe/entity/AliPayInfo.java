package com.binvshe.binvshe.entity;

/**
 * Created by chenjy on 2015/12/25.
 */
public class AliPayInfo {
    private String linek;

    private String orderNo;

    @Override
    public String toString() {
        return "AliPayInfo{" +
                "linek='" + linek + '\'' +
                ", orderNo='" + orderNo + '\'' +
                '}';
    }

    public String getLinek() {
        return linek;
    }

    public void setLinek(String linek) {
        this.linek = linek;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
