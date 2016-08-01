package com.binvshe.binvshe.http.response;


/**
 * Created by Administrator on 2015/11/19.
 */
public class ImageUrlResponse extends BaseResponse {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ImageUrlResponse{" +
                "data=" + data +
                '}';
    }
}
