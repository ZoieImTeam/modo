package com.binvshe.binvshe.binvsheui.chen.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chenjy on 2015/11/20.
 */
public class PrivacyUtils {

    public static  void saveCanDLphoto(Context context, boolean isChecked){
        SharedPreferences sp = context.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.PRIVACY, Context.MODE_PRIVATE);
        sp.edit().putBoolean(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.PHOTO, isChecked).commit();
    }
    public static boolean readCanDLphoto(Context context){
        SharedPreferences sp = context.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.PRIVACY, Context.MODE_PRIVATE);
        boolean isCanDLphoto = sp.getBoolean(Constants.SP_KEY.PHOTO, true);
        return isCanDLphoto;
    }

}
