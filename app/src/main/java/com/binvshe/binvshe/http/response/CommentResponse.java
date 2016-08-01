package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.Comment;

public class CommentResponse extends BaseResponse {
    private Comment data;

    @Override
    public String toString() {
        return "CommentResponse{" +
                "data=" + data +
                '}';
    }

    public Comment getData() {
        return data;
    }

    public void setData(Comment data) {
        this.data = data;
    }
}
