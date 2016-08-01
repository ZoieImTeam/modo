package com.binvshe.binvshe.binvsheui.RongCloud;

import android.util.Log;


import com.binvshe.binvshe.app.BaseApp;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Administrator on 2016/2/2.
 */
public class JPushUtils {

    public static void SettingsAlis(String alis) {
        JPushInterface.setAlias(BaseApp.mContext, alis, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                if (i == 0) {
                    Log.i("JPushInterface.setAlias", "alias设置成功" + "  alias:" + s);
                } else {
                    Log.e("JPushInterface.setAlias", "错误码:" + i + "  alias:" + s);
                }
            }
        });
    }
}
