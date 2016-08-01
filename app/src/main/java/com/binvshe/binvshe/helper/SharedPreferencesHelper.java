package com.binvshe.binvshe.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.binvshe.binvshe.app.BaseApp;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/5/24
 */
public class SharedPreferencesHelper {
    public static void savespint(String key,int value){
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences("binvshe", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key,value);
        edit.apply();
    }
    public static int getSpInt(String key,int defult)
    {
        SharedPreferences settings =BaseApp.mContext.getSharedPreferences("binvshe", Context.MODE_PRIVATE);
        int value=settings.getInt(key,defult);
        return value;
    }
}
