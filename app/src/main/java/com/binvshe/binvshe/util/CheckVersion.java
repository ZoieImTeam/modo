package com.binvshe.binvshe.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import com.binvshe.binvshe.http.model.GetVersionModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;

import org.srr.dev.base.BaseActivity;
import org.srr.dev.base.BaseApplication;

/**
 * Created by Cainer on 2016/4/21.
 */
public class CheckVersion {

//    public static GetVersionModel getVersionModel=new GetVersionModel();
//    private static CheckVersion mCheckVersion;

    public static String getVersion()
    {
        PackageInfo info;
        try {
            //info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            Context context=BaseApplication.mContext;
            info= context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            // 当前应用的版本名称
            String versionName = info.versionName;
            // 当前版本的版本号
            int versionCode = info.versionCode;
            // 当前版本的包名
            String packageNames = info.packageName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

//    private CheckVersion() {
//    }
//
//    public static CheckVersion getInstance(){
//        if (mCheckVersion==null){
//            mCheckVersion = new CheckVersion();
//        }
//        return mCheckVersion;
//    }
//    public static void isNewVersion()
//    {
//
//        getVersionModel.start();
//        getVersionModel.setViewModelInterface(CheckVersion.getInstance());
//    }
//
//    @Override
//    public Handler getHandler() {
//        return null;
//    }
//
//    @Override
//    public void onPreLoad(int tag) {
//
//    }
//
//    @Override
//    public void onSuccessLoad(int tag, Object result) {
//
//    }
//
//    @Override
//    public void onFailLoad(int tag, int code, String codeMsg) {
//
//    }
//
//    @Override
//    public void onExceptionLoad(int tag, Exception exception) {
//
//    }
}
