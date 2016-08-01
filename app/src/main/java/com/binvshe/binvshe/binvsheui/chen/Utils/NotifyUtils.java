package com.binvshe.binvshe.binvsheui.chen.Utils;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * Created by chenjy on 2015/11/19.
 */
public class NotifyUtils {
    public static void saveVoice(Context context, boolean isChecked){
        SharedPreferences sp = context.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.NOTIFY, Context.MODE_PRIVATE);
        sp.edit().putBoolean(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.VOICE, isChecked).commit();
    }
    public static boolean isVoice(Context context){
        SharedPreferences sp = context.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.NOTIFY, Context.MODE_PRIVATE);
        boolean isVoice = sp.getBoolean(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.VOICE, true);
        return isVoice;
    }

    public static void saveShake(Context context, boolean isChecked){
        SharedPreferences sp = context.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.NOTIFY, Context.MODE_PRIVATE);
        sp.edit().putBoolean(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.SHAKE, isChecked).commit();
    }
    public static boolean isShake(Context context){
        SharedPreferences sp = context.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.NOTIFY, Context.MODE_PRIVATE);
        boolean isShake = sp.getBoolean(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.SHAKE, false);
        return isShake;
    }

    public static void saveGetNewMsg(Context context, boolean isChecked){
        SharedPreferences sp = context.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.NOTIFY, Context.MODE_PRIVATE);
        sp.edit().putBoolean(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.NEWMSG, isChecked).commit();
    }
    public static boolean isNewMsg(Context context){
        SharedPreferences sp = context.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.NOTIFY, Context.MODE_PRIVATE);
        boolean isNewMsg = sp.getBoolean(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.NEWMSG, true);
        return isNewMsg;
    }
}
