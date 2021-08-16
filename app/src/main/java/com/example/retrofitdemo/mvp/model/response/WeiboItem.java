package com.example.retrofitdemo.mvp.model.response;

import java.util.Arrays;

import lombok.Data;


public class WeiboItem {
    private int id;
    private String avatarUrl;
    private String username;
    private String content;
    private String[] imgUrls;
    private String[] viddoUrls;
    private String[] tags;
    private int commentCount;
    private int commentId;
    private int voteCount;
    private int shareCount;
    private String createTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String[] imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String[] getViddoUrls() {
        return viddoUrls;
    }

    public void setViddoUrls(String[] viddoUrls) {
        this.viddoUrls = viddoUrls;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WeiboItem{" +
                "id=" + id +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", imgUrls=" + Arrays.toString(imgUrls) +
                ", viddoUrls=" + Arrays.toString(viddoUrls) +
                ", tags=" + Arrays.toString(tags) +
                ", commentCount=" + commentCount +
                ", commentId=" + commentId +
                ", voteCount=" + voteCount +
                ", shareCount=" + shareCount +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
