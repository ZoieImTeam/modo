package com.binvshe.binvshe.entity.ActivityList;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class GetActivityList {

    @Expose
    private Integer curPage;
    @Expose
    private List<ActivityData> datas = new ArrayList<ActivityData>();
    @Expose
    private Boolean next;
    @Expose
    private Integer pageCount;
    @Expose
    private Integer pagesize;
    @Expose
    private Boolean pre;
    @Expose
    private Integer rowCount;

    /**
     * 
     * @return
     *     The curPage
     */
    public Integer getCurPage() {
        return curPage;
    }

    /**
     * 
     * @param curPage
     *     The curPage
     */
    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    /**
     * 
     * @return
     *     The datas
     */
    public List<ActivityData> getDatas() {
        return datas;
    }

    /**
     * 
     * @param datas
     *     The datas
     */
    public void setDatas(List<ActivityData> datas) {
        this.datas = datas;
    }

    /**
     * 
     * @return
     *     The next
     */
    public Boolean getNext() {
        return next;
    }

    /**
     * 
     * @param next
     *     The next
     */
    public void setNext(Boolean next) {
        this.next = next;
    }

    /**
     * 
     * @return
     *     The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * 
     * @param pageCount
     *     The pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 
     * @return
     *     The pagesize
     */
    public Integer getPagesize() {
        return pagesize;
    }

    /**
     * 
     * @param pagesize
     *     The pagesize
     */
    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    /**
     * 
     * @return
     *     The pre
     */
    public Boolean getPre() {
        return pre;
    }

    /**
     * 
     * @param pre
     *     The pre
     */
    public void setPre(Boolean pre) {
        this.pre = pre;
    }

    /**
     * 
     * @return
     *     The rowCount
     */
    public Integer getRowCount() {
        return rowCount;
    }

    /**
     * 
     * @param rowCount
     *     The rowCount
     */
    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public String toString() {
        return "GetActivityList{" +
                "curPage=" + curPage +
                ", datas=" + datas +
                ", next=" + next +
                ", pageCount=" + pageCount +
                ", pagesize=" + pagesize +
                ", pre=" + pre +
                ", rowCount=" + rowCount +
                '}';
    }
}
