package com.binvshe.binvshe.entity;

import com.binvshe.binvshe.entity.subject.SubjectEntity;

import java.util.List;

/**
 * Created by Cainer on 2016/4/21.
 */
public class MyLikeSpecialEntity {
    private int curPage;
    private boolean next;
    private int pageCount;
    private int pagesize;
    private boolean pre;
    private int rowCount;
    private List<SubjectEntity> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public boolean isPre() {
        return pre;
    }

    public void setPre(boolean pre) {
        this.pre = pre;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<SubjectEntity> getDatas() {
        return datas;
    }

    public void setDatas(List<SubjectEntity> datas) {
        this.datas = datas;
    }

}
