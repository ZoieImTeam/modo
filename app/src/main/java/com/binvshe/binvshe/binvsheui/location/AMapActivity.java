package com.binvshe.binvshe.binvsheui.location;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.binvshe.binvshe.R;
import com.binvshe.binvshe.app.BaseApp;
import com.binvshe.binvshe.binvsheui.RongCloud.MyConnectionStatusListener;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;

import io.rong.message.LocationMessage;

/**
 * AMapV1地图中简单介绍显示定位小蓝点
 */
public class AMapActivity extends AbsFragmentActivity implements LocationSource,
        AMapLocationListener, Handler.Callback {
    private AMap aMap;
    private MapView mapView;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private HandlerThread mHandlerThread;
    Handler mWorkHandler;
    Handler mHandler;
    LocationMessage mMsg;
    private final static int RENDER_POI = 1;
    private final static int SHWO_TIPS = 2;
    public static final String RESULT_LOCATION = "result_location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseApp.addDestoryActivity(this, toString());
        MyConnectionStatusListener.getInstance().setActivity(this);

        mHandlerThread = new HandlerThread("LocationThread");
        mHandlerThread.start();
        mWorkHandler = new Handler(mHandlerThread.getLooper());
        mHandler = new Handler(this);
        findViewById(R.id.send_location).setOnClickListener(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();

        if (getIntent().hasExtra("location")) {
            mMsg = getIntent().getParcelableExtra("location");
        }
    }

    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_amap;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClickView(View view, int id) {
        switch (id) {
            case R.id.send_location:
                if (mMsg != null) {
//                    com.binvshe.binvshe.ui.message.LocationProvider.getInstance().getLastLocationCallback().onSuccess(mMsg);
//                    com.binvshe.binvshe.ui.message.LocationProvider.getInstance().setLastLocationCallback(null);
                    BaseApp.removeActivity(toString());
                    finish();
                } else {
//                    com.binvshe.binvshe.ui.message.LocationProvider.getInstance().getLastLocationCallback()
//                            .onFailure("定位失败");
                }
                break;
            default:
        }
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
//        if (com.binvshe.binvshe.ui.message.LocationProvider.getInstance().getLastLocationCallback() != null)
//            com.binvshe.binvshe.ui.message.LocationProvider.getInstance().getLastLocationCallback().onFailure("失败");
        LocationProvider.getInstance().setLastLocationCallback(null);
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                mHandler.obtainMessage(SHWO_TIPS, amapLocation).sendToTarget();
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
            mLocationOption.setOnceLocation(true);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public boolean handleMessage(Message msg) {

        AMapLocation location = (AMapLocation) msg.obj;
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        String address = location.getAddress();
        String locations = location.getCountry() + location.getProvince() + location.getCity() + location.getDistrict() + location.getAddress();
        Uri uri = Uri.parse("http://m.amap.com/?q=" +
                lat + "," + lon +
                "&name=" + locations);
        mMsg = LocationMessage.obtain(lat, lon, address, uri);

        return false;
    }

    @Override
    public void refreshData() {

    }
}
