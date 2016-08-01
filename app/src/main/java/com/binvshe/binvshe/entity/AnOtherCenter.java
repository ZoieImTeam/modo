package com.binvshe.binvshe.entity;

/**
 * Created by Cainer on 2016/3/24.
 */
public class AnOtherCenter {
        private int fans;
        private int attention;
        /**
         * id : 127
         * name : 阿慕
         * sex : 1
         * sign :
         * bgurl : null
         * birthday : 1999-11-30
         * job :
         * company :
         * school :
         * address :
         * user_x : 0
         * user_y : 0
         * head : http://114.215.119.51:80//binvsheApp/resources/images/defauthead.png
         */

        private UserinfoBean userinfo;

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getAttention() {
            return attention;
        }

        public void setAttention(int attention) {
            this.attention = attention;
        }

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean {
            private int id;
            private String name;
            private int sex;
            private String sign;
            private Object bgurl;
            private String birthday;
            private String job;
            private String company;
            private String school;
            private String address;
            private String user_x;
            private String user_y;
            private String head;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public Object getBgurl() {
                return bgurl;
            }

            public void setBgurl(Object bgurl) {
                this.bgurl = bgurl;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getUser_x() {
                return user_x;
            }

            public void setUser_x(String user_x) {
                this.user_x = user_x;
            }

            public String getUser_y() {
                return user_y;
            }

            public void setUser_y(String user_y) {
                this.user_y = user_y;
            }

            public String getHead() {
                return head;
            }

            public void setHead(String head) {
                this.head = head;
            }
        }
    }

