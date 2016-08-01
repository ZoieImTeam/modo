package com.binvshe.binvshe.entity.channel;

import com.binvshe.binvshe.entity.Dynamic;

import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public class ChannelItem {
    private int curPage;
    private boolean next;
    private int pageCount;
    private int pagesize;
    private boolean pre;
    private int rowCount;

    private List<Dynamic> datas;

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public void setPre(boolean pre) {
        this.pre = pre;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setDatas(List<Dynamic> datas) {
        this.datas = datas;
    }

    public int getCurPage() {
        return curPage;
    }

    public boolean isNext() {
        return next;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPagesize() {
        return pagesize;
    }

    public boolean isPre() {
        return pre;
    }

    public int getRowCount() {
        return rowCount;
    }

    public List<Dynamic> getDatas() {
        return datas;
    }

}
