package com.binvshe.binvshe.binvsheui.RongCloud;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.chen.Utils.MyselfUtils;
import com.binvshe.binvshe.binvsheui.login.LoginActivity;
import com.binvshe.binvshe.db.dao.BlacklistFormDao;
import com.binvshe.binvshe.db.dao.BlacklistToDao;
import com.binvshe.binvshe.db.dao.UserDao;

import io.rong.imlib.RongIMClient;

/**
 * Created by Administrator on 2016/1/18.
 */
public class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {
    private static MyConnectionStatusListener connectionStatusListener;
    private static FragmentActivity mActivity;

    public static MyConnectionStatusListener getInstance() {
        if (connectionStatusListener == null) {
            connectionStatusListener = new MyConnectionStatusListener();
            return connectionStatusListener;
        } else {
            return connectionStatusListener;
        }
    }

    public void setActivity(FragmentActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onChanged(ConnectionStatus connectionStatus) {

        switch (connectionStatus) {

            case NETWORK_UNAVAILABLE://网络不可用。
                Log.e("value", "NETWORK_UNAVAILABLE 网络不可用");
                break;
            case CONNECTED://连接成功。
                Log.e("value", "CONNECTED  连接成功");
                break;
            case CONNECTING://连接中。
                Log.e("value", "CONNECTING  连接中");
                break;
            case DISCONNECTED://断开连接。
                Log.e("value", "DISCONNECTED  断开连接");
                break;
            case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                Log.e("value", "KICKED_OFFLINE_BY_OTHER_CLIENT  用户账户在其他设备登录，本机会被踢掉线");
                kickedOfflineByOtherClient();
                break;
            case TOKEN_INCORRECT://token不对
                Log.e("value", "TOKEN_INCORRECT  token不对");
                break;
            case SERVER_INVALID://服务器无效
                Log.e("value", "SERVER_INVALID  服务器无效");
                break;
        }
    }

    public void kickedOfflineByOtherClient() {
        if (mActivity != null) {
            TextDialogFragment dialog = new TextDialogFragment();
            dialog.setOnEnterButtonListener(new TextDialogFragment.OnEnterButtonListener() {
                @Override
                public void clickEnter() {
                    MyselfUtils.saveLoginStatus(false);
                    // 2015/12/8  清空数据库
                    new BlacklistFormDao(mActivity).clean();
                    new BlacklistToDao(mActivity).clean();
                    new UserDao(mActivity).clean();
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    mActivity.startActivity(intent);
                    BaseApp.destoryAllActivity();
                }
            });

            dialog.show(mActivity.getSupportFragmentManager(), "dialog_exit");
        }
    }
}