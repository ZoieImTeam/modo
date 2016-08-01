package com.binvshe.binvshe.http.response;

import com.binvshe.binvshe.entity.UserLogin.UserLogin;

/**
 * Created by chenjy on 2015/12/1.
 */
public class LoginResponse extends BaseResponse {

    private UserLogin data;

    public UserLogin getData() {
        return data;
    }

    public void setData(UserLogin data) {
        data = data;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "data=" + data +
                '}';
    }
}
