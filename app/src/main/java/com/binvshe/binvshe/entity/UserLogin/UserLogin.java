package com.binvshe.binvshe.entity.UserLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserLogin {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "message='" + message + '\'' +
                ", blacklistTo=" + blacklistTo +
                ", attentions=" + attentions +
                ", friends=" + friends +
                ", fans=" + fans +
                ", foryaoqing=" + foryaoqing +
                ", likes=" + likes +
                ", nolike=" + nolike +
                ", blacklistForm=" + blacklistForm +
                ", user=" + user +
                ", between=" + between +
                '}';
    }

    @Expose
    private String message;
    @SerializedName("blacklist_to")
    @Expose
    private List<Blacklistto> blacklistTo = new ArrayList<Blacklistto>();
    @Expose
    private List<Attention> attentions = new ArrayList<Attention>();
    @Expose
    private List<Friend> friends = new ArrayList<Friend>();
    @Expose
    private List<Fan> fans = new ArrayList<Fan>();
    @Expose
    private List<Foryaoqing> foryaoqing = new ArrayList<Foryaoqing>();
    @Expose
    private List<Like> likes = new ArrayList<Like>();
    @Expose
    private List<Nolike> nolike = new ArrayList<Nolike>();
    @SerializedName("blacklist_form")
    @Expose
    private List<BlacklistForm> blacklistForm = new ArrayList<BlacklistForm>();
    @Expose
    private User user;
    @Expose
    private List<Between> between = new ArrayList<Between>();

    /**
     * @return The blacklistTo
     */
    public List<Blacklistto> getBlacklistTo() {
        return blacklistTo;
    }

    /**
     * @param blacklistTo The blacklist_to
     */
    public void setBlacklistTo(List<Blacklistto> blacklistTo) {
        this.blacklistTo = blacklistTo;
    }

    /**
     * @return The attentions
     */
    public List<Attention> getAttentions() {
        return attentions;
    }

    /**
     * @param attentions The attentions
     */
    public void setAttentions(List<Attention> attentions) {
        this.attentions = attentions;
    }


    /**
     * @return The friends
     */
    public List<Friend> getFriends() {
        return friends;
    }

    /**
     * @param friends The friends
     */
    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    /**
     * @return The fans
     */
    public List<Fan> getFans() {
        return fans;
    }

    /**
     * @param fans The fans
     */
    public void setFans(List<Fan> fans) {
        this.fans = fans;
    }

    /**
     * @return The foryaoqing
     */
    public List<Foryaoqing> getForyaoqing() {
        return foryaoqing;
    }

    /**
     * @param foryaoqing The foryaoqing
     */
    public void setForyaoqing(List<Foryaoqing> foryaoqing) {
        this.foryaoqing = foryaoqing;
    }

    /**
     * @return The likes
     */
    public List<Like> getLikes() {
        return likes;
    }

    /**
     * @param likes The likes
     */
    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    /**
     * @return The nolike
     */
    public List<Nolike> getNolike() {
        return nolike;
    }

    /**
     * @param nolike The nolike
     */
    public void setNolike(List<Nolike> nolike) {
        this.nolike = nolike;
    }

    /**
     * @return The blacklistForm
     */
    public List<BlacklistForm> getBlacklistForm() {
        return blacklistForm;
    }

    /**
     * @param blacklistForm The blacklist_form
     */
    public void setBlacklistForm(List<BlacklistForm> blacklistForm) {
        this.blacklistForm = blacklistForm;
    }

    /**
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return The between
     */
    public List<Between> getBetween() {
        return between;
    }

    /**
     * @param between The between
     */
    public void setBetween(List<Between> between) {
        this.between = between;
    }

}
