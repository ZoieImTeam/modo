package com.binvshe.binvshe.binvsheui.chen.Utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.binvshe.binvshe.app.BaseApp;

import static com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY;
import static com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME;

/**
 * Created by chenjy on 2016/1/7.
 */
public class MyselfUtils {

    /**
     * 保存当前登录状态
     *
     * @param loginStatus
     */
    public static void saveLoginStatus(boolean loginStatus) {
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(SP_NAME.MYSELF, BaseApp.mContext.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putBoolean(SP_KEY.LOGIN_STATUS, loginStatus);
        edit.commit();
    }

    /**
     * 获取当前登录状态
     *
     * @return
     */
    public static boolean getLoginStatus() {
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(Constants.SP_NAME.MYSELF, BaseApp.mContext.MODE_PRIVATE);
        return sp.getBoolean(Constants.SP_KEY.LOGIN_STATUS, false);
    }

    /**
     * 保存登录账号密码
     *
     * @param user
     * @param pwd
     */
    public static void saveLoginInfo(String user, String pwd) {
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.MYSELF, BaseApp.mContext.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putString(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.LOGIN_USER, user);
        edit.putString(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.LOGIN_PWD, pwd);
    }

    /**
     * 获取用户登录信息
     *
     * @return
     */
    public static String getLoginInfo() {
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.MYSELF, BaseApp.mContext.MODE_PRIVATE);
        return sp.getString(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.LOGIN_USER, "") + "," + sp.getString(SP_KEY.LOGIN_PWD, "");
    }

}
