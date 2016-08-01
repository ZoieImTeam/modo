package com.binvshe.binvshe.binvsheui.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.chen.Utils.Constants;

/**
 * Created by chenjy on 2016/2/15.
 */
public class SpUtils {

    private static Context context = BaseApp.mContext;

    // 是否处于登录状态
    public static void saveLoginStatus(boolean isLogin){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.USER, Context.MODE_PRIVATE);
        sp.edit().putBoolean(Constants.SP_KEY.LOGIN_STATUS, isLogin).commit();
    }

    public static boolean isLogin(){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.USER, Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean(Constants.SP_KEY.LOGIN_STATUS, false);
        return isLogin;
    }

    // 保存/获取用户ID
    public static void saveUserID(String userID){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.USER, Context.MODE_PRIVATE);
        sp.edit().putString(Constants.SP_KEY.USERID, userID).commit();
    }

    public static String getUserID(){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.USER, Context.MODE_PRIVATE);
        String userID = sp.getString(Constants.SP_KEY.USERID, "0");
        return userID;
    }

}
