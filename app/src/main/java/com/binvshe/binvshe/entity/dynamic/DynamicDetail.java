package com.binvshe.binvshe.entity.dynamic;

import com.binvshe.binvshe.entity.UserLogin.User;

import java.util.List;

/**
 * Created by Administrator on 2016/2/17.
 */
public class DynamicDetail {

    private String isAttention;
    private DynamicRes resources;
    private DynamicSpe special;
    private User userinfo;
    private List<UserTag> userlable;
    private String replyCount;

    public void setResources(DynamicRes resources) {
        this.resources = resources;
    }

    public void setSpecial(DynamicSpe special) {
        this.special = special;
    }

    public void setUserinfo(User userinfo) {
        this.userinfo = userinfo;
    }

    public void setUserlable(List<UserTag> userlable) {
        this.userlable = userlable;
    }

    public DynamicRes getResources() {
        return resources;
    }

    public DynamicSpe getSpecial() {
        return special;
    }

    public User getUserinfo() {
        return userinfo;
    }

    public List<UserTag> getUserlable() {
        return userlable;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public String getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(String isAttention) {
        this.isAttention = isAttention;
    }
}
