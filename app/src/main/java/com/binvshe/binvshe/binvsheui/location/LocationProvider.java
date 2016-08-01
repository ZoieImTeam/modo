package com.binvshe.binvshe.binvsheui.location;

import android.content.Context;
import android.content.Intent;


import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2016/1/7.
 */
public class LocationProvider implements RongIM.LocationProvider {

    private static LocationProvider locationProvider;

    public static LocationProvider getInstance() {

        if (locationProvider == null) {
            locationProvider = new LocationProvider();
        }
        return locationProvider;
    }

    private LocationCallback mLastLocationCallback;

    /**
     * 位置信息提供者:LocationProvider 的回调方法，打开第三方地图页面。
     *
     * @param context  上下文
     * @param callback 回调
     */
    @Override
    public void onStartLocation(Context context, LocationCallback callback) {
        LocationProvider.getInstance().setLastLocationCallback(callback);
        Intent intent = new Intent(context, AMapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);//AMap地图
    }

    public LocationCallback getLastLocationCallback() {
        return mLastLocationCallback;
    }

    public void setLastLocationCallback(LocationCallback lastLocationCallback) {
        this.mLastLocationCallback = lastLocationCallback;
    }
}