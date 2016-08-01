package com.binvshe.binvshe.binvsheui.chen.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.binvshe.binvshe.app.BaseApp;

/**
 * Created by chenjy on 2015/12/10.
 */
public class EditPersonDatas {

    public static com.binvshe.binvshe.binvsheui.chen.Utils.EditPersonDatas editPersonDatas;

    public static com.binvshe.binvshe.binvsheui.chen.Utils.EditPersonDatas getEditPersonDatas() {
        if (editPersonDatas == null) {
            editPersonDatas = new com.binvshe.binvshe.binvsheui.chen.Utils.EditPersonDatas();
            return editPersonDatas;
        }
        return editPersonDatas;
    }

    public void saveEditNickname( String nickname) {
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.EDIT_NICKNAME, nickname);
        edit.commit();
    }

//    public void saveEditJob(Context context, String job) {
//        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putString(Constants.SP_KEY.EDIT_JOB, job);
//        edit.commit();
//    }
//
//    public void saveEditCommeny(Context context, String commeny) {
//        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putString(Constants.SP_KEY.EDIT_COMMENY, commeny);
//        edit.commit();
//    }
//
//    public void saveEditUniversitu(Context context, String university) {
//        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putString(Constants.SP_KEY.EDIT_UNIVERSITY, university);
//        edit.commit();
//    }
//
//    public void saveEditAddress(Context context, String address) {
//        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putString(Constants.SP_KEY.EDIT_ADDRESS, address);
//        edit.commit();
//    }
//
//    public void saveEditSign(Context context, String sign) {
//        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putString(Constants.SP_KEY.EDIT_SIGN, sign);
//        edit.commit();
//    }
//
    public void saveEditImageHead( String imageUrl) {
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.EDIT_IMAGEURL, imageUrl);
        edit.commit();
    }

    public void saveUserID(Context context, String userId) {
        SharedPreferences sp = context.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.USERID, userId);
        edit.commit();
    }

    public void saveIsEditHead(boolean isEditHead){
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.ISEDITHEAD, isEditHead);
        edit.commit();
    }

    // 读取
    public String getEditNickname(){
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
        return sp.getString(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.EDIT_NICKNAME,"null");
    }

//    public String getEditJob(Context context){
//        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
//        return sp.getString(Constants.SP_KEY.EDIT_JOB, "null");
//    }
//
//    public String getEditCommeny(Context context){
//        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
//        return sp.getString(Constants.SP_KEY.EDIT_COMMENY,"null");
//    }
//
//    public String getEditUniversity(Context context){
//        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
//        return sp.getString(Constants.SP_KEY.EDIT_UNIVERSITY, "null");
//    }
//
//    public String getEditAddress(Context context){
//        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
//        return sp.getString(Constants.SP_KEY.EDIT_ADDRESS, "null");
//    }
//    public String getEditSign(Context context){
//        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
//        return sp.getString(Constants.SP_KEY.EDIT_SIGN, "null");
//    }

    public String getEditImageHead() {
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
        return sp.getString(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.EDIT_IMAGEURL, "null");
    }

    public String getUserID(Context context) {
        SharedPreferences sp = context.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
        return sp.getString(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.USERID, "null");
    }

    public boolean isEditHead(){
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
        return sp.getBoolean(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_KEY.ISEDITHEAD, false);
    }

  public String getUserID() {
        SharedPreferences sp = BaseApp.mContext.getSharedPreferences(com.binvshe.binvshe.binvsheui.chen.Utils.Constants.SP_NAME.EDITPERSONDATA, Activity.MODE_PRIVATE);
        return sp.getString(Constants.SP_KEY.USERID, "null");
    }
}
