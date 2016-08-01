package com.binvshe.binvshe.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class Comment {
    private int curPage;
    private boolean next;
    private int pageCount;
    private int pagesize;
    private boolean pre;
    private int rowCount;

    private List<CommentData> datas;

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

    public void setDatas(List<CommentData> datas) {
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

    public List<CommentData> getDatas() {
        return datas;
    }

    public static class CommentData {
        private int replysid;
        private int activity_id;
        private int usera_id;
        private String usera_name;
        private String usera_img;
        private String userb_name;
        private String userb_img;
        private int userb_id;
        private String photos;
        private int is_del;
        private String createdate;
        private String content;
        private int rid;
        private int lou;
        private int resources;

        public void setReplysid(int replysid) {
            this.replysid = replysid;
        }

        public void setActivity_id(int activity_id) {
            this.activity_id = activity_id;
        }

        public void setUsera_id(int usera_id) {
            this.usera_id = usera_id;
        }

        public void setUsera_name(String usera_name) {
            this.usera_name = usera_name;
        }

        public void setUsera_img(String usera_img) {
            this.usera_img = usera_img;
        }

        public void setUserb_name(String userb_name) {
            this.userb_name = userb_name;
        }

        public void setUserb_img(String userb_img) {
            this.userb_img = userb_img;
        }

        public void setUserb_id(int userb_id) {
            this.userb_id = userb_id;
        }

        public void setPhotos(String photos) {
            this.photos = photos;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public void setLou(int lou) {
            this.lou = lou;
        }

        public void setResources(int resources) {
            this.resources = resources;
        }

        public int getReplysid() {
            return replysid;
        }

        public int getActivity_id() {
            return activity_id;
        }

        public int getUsera_id() {
            return usera_id;
        }

        public String getUsera_name() {
            return usera_name;
        }

        public String getUsera_img() {
            return usera_img;
        }

        public String getUserb_name() {
            return userb_name;
        }

        public String getUserb_img() {
            return userb_img;
        }

        public int getUserb_id() {
            return userb_id;
        }

        public String getPhotos() {
            return photos;
        }

        public int getIs_del() {
            return is_del;
        }

        public String getCreatedate() {
            return createdate;
        }

        public String getContent() {
            return content;
        }

        public int getRid() {
            return rid;
        }

        public int getLou() {
            return lou;
        }

        public int getResources() {
            return resources;
        }
    }
}
