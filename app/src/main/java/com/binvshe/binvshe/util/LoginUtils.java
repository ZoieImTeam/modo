package com.binvshe.binvshe.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.binvshe.binvshe.binvsheui.login.LoginActivity;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;

public class LoginUtils {

    public static boolean isLogin(Context context, Activity activity) {
        if (!SpUtils.isLogin()) {
            Toast.makeText(activity, "请登录...", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(activity, LoginActivity.class));
            return true;
        }
        return false;
    }
    public static boolean isLogin(Fragment context, Activity activity) {
        if (!SpUtils.isLogin()) {
            Toast.makeText(activity, "请登录...", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(activity, LoginActivity.class));
            return true;
        }
        return false;
    }
}