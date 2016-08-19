package com.binvshe.binvshe.app;

import com.binvshe.binvshe.http.base.HttpRequestManager;

import org.srr.dev.base.BaseApplication;

import cn.sharesdk.framework.ShareSDK;
import io.rong.imkit.RongIM;

public class BaseApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpRequestManager.getInstance().initRequestQueue(this);
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);
            /**
             * 检测内存泄漏
             */
        }

        ShareSDK.initSDK(BaseApp.mContext);
    }



}
