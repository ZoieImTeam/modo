package com.binvshe.binvshe.binvsheui.chen.Utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by chenjy on 2015/11/19.
 */
public class SafeUtils {
    public static void bindPhone(Context context, boolean isChecked){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.SAFE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(Constants.SP_KEY.BINDPHONE, isChecked).commit();
    }
    public static boolean isBindPhone(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.SAFE, Context.MODE_PRIVATE);
        boolean isBind = sp.getBoolean(Constants.SP_KEY.BINDPHONE, false);
        return isBind;
    }

    public static void usingAdrlist(Context context, boolean isChecked){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.SAFE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(Constants.SP_KEY.USINGADRLIST, isChecked).commit();
    }
    public static boolean isUsingAdrlist(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.SAFE, Context.MODE_PRIVATE);
        boolean isUsing = sp.getBoolean(Constants.SP_KEY.USINGADRLIST, false);
        return isUsing;
    }

    public static void weixin(Context context, boolean isChecked){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.SAFE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(Constants.SP_KEY.WEIXIN, isChecked).commit();
    }
    public static boolean isWeixin(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.SAFE, Context.MODE_PRIVATE);
        boolean isWeixin = sp.getBoolean(Constants.SP_KEY.WEIXIN, false);
        return isWeixin;
    }
}
