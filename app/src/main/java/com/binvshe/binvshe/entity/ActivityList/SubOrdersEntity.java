package com.binvshe.binvshe.entity.ActivityList;

import java.util.List;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/8
 */
public  class SubOrdersEntity {
    private String id;
    /**
     * activityId : 90
     * cardno :
     * cardtype :
     * createTime : 1470559974000
     * del : false
     * discount : 0
     * id : 862
     * name : 942be604605526882a44f62fa19f5f56
     * numcode : T134822566
     * orderId : 1348
     * photos :
     * price : 160
     * productId : 5
     * qrcodeId : 5acc58a5-f45e-4cb5-bbf0-a17d0e215e7e
     * timestamp : 1470559974000
     * token :
     * used : false
     * user : 314
     * usermobile :
     */

    private List<TicketsEntity> tickets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TicketsEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketsEntity> tickets) {
        this.tickets = tickets;
    }

    public static class TicketsEntity {
        private int activityId;
        private String cardno;
        private String cardtype;
        private long createTime;
        private boolean del;
        private int discount;
        private int id;
        private String name;
        private String numcode;
        private int orderId;
        private String photos;
        private double price;
        private int productId;
        private String qrcodeId;
        private long timestamp;
        private String token;
        private boolean used;
        private int user;
        private String usermobile;

        public int getActivityId() {
            return activityId;
        }

        public void setActivityId(int activityId) {
            this.activityId = activityId;
        }

        public String getCardno() {
            return cardno;
        }

        public void setCardno(String cardno) {
            this.cardno = cardno;
        }

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public boolean isDel() {
            return del;
        }

        public void setDel(boolean del) {
            this.del = del;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
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

        public String getNumcode() {
            return numcode;
        }

        public void setNumcode(String numcode) {
            this.numcode = numcode;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getPhotos() {
            return photos;
        }

        public void setPhotos(String photos) {
            this.photos = photos;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getQrcodeId() {
            return qrcodeId;
        }

        public void setQrcodeId(String qrcodeId) {
            this.qrcodeId = qrcodeId;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public int getUser() {
            return user;
        }

        public void setUser(int user) {
            this.user = user;
        }

        public String getUsermobile() {
            return usermobile;
        }

        public void setUsermobile(String usermobile) {
            this.usermobile = usermobile;
        }
    }
}
