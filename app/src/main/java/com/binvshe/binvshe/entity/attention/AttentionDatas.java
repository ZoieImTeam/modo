package com.binvshe.binvshe.entity.attention;

import com.binvshe.binvshe.entity.subject.SubjectEntity;

import java.util.List;

/**
 * Created by Cainer on 2016/3/28.
 */
public class AttentionDatas {
        private int id;
        private int uid;
        private String name;



    private String fans;
        private String head;
        private int sex;
        private Object job;
        /**
         * id : 151
         * systype : 19
         * typename : coser
         * pid : 5
         * pname : COS
         * name : Asdfdsafasd
         * user : 162
         * photos : http://114.215.119.51:80/datas/files/binvsheApp/20160325390/1458896155354.png
         * lable : null
         * createdate : 2016-03-25 16:03
         * desc : Fasfasdfasfasfadsf
         * browsenumber : 3
         * likeCount : 0
         * serial : 1
         * showtype : 1
         */

        private List<SubjectEntity> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getJob() {
            return job;
        }

        public void setJob(Object job) {
            this.job = job;
        }

        public List<SubjectEntity> getList() {
            return list;
        }

        public void setList(List<SubjectEntity> list) {
            this.list = list;
        }

}
