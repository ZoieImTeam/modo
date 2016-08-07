package com.binvshe.binvshe.entity.ActivityList;

import java.util.List;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/7
 */
public class SelectDateFirnData {


        private int activityId;
        private String code;
        private int displayorder;
        private int id;
        private String name;
        private int parentId;
        /**
         * activityId : 90
         * children : []
         * code :
         * displayorder : 1
         * id : 3
         * name : 10月1日星期六
         * parentId : 1
         */

        private List<ChildrenEntity> children;

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getDisplayorder() {
            return displayorder;
        }

        public void setDisplayorder(int displayorder) {
            this.displayorder = displayorder;
        }

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

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public List<ChildrenEntity> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenEntity> children) {
            this.children = children;
        }

        public static class ChildrenEntity {
            private int activityId;
            private String code;
            private int displayorder;
            private int id;
            private String name;
            private int parentId;
            private List<?> children;
            private boolean isSelected=false;

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(int displayorder) {
                this.displayorder = displayorder;
            }

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

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }
        }

}

