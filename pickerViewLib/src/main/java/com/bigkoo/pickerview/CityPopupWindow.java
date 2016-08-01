package com.bigkoo.pickerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.bigkoo.pickerview.lib.ScreenInfo;
import com.bigkoo.pickerview.lib.WheelCity;
import com.bigkoo.pickerview.lib.WheelView;

/**
 * Created by Administrator on 2015/10/9.
 */
public class CityPopupWindow extends PopupWindow implements View.OnClickListener {

    private View rootView; // 总的布局
    WheelCity wheelCity;
    private View btnSubmit, btnCancel;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private OnCitySelectListener citySelectListener;
    public static final int TYPE_AREA = 3;
    public static final int TYPE_CITY = 2;
    public static final int TYPE_PROVINCE = 1;
    private WheelView mArea,mCity,mProvince;


    /**
     *
     * @param context
     * @param showArea 是否显示地区那一栏
     */
    public CityPopupWindow(Context context,boolean showArea) {
        super(context);
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new BitmapDrawable());// 这样设置才能点击屏幕外dismiss窗口
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.timepopwindow_anim_style);

        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        rootView = mLayoutInflater.inflate(R.layout.pw_city, null);
        // -----确定和取消按钮
        btnSubmit = rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = rootView.findViewById(R.id.pw_btn_cancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        // ----时间转轮
        final View citypicker = rootView.findViewById(R.id.citypicker);

        mArea = (WheelView) citypicker.findViewById(R.id.area);
        mCity = (WheelView) citypicker.findViewById(R.id.city);
        mProvince = (WheelView) citypicker.findViewById(R.id.province);
        ScreenInfo screenInfo = new ScreenInfo((Activity) context);
        wheelCity = new WheelCity(citypicker,showArea,screenInfo.getHeight());


        setContentView(rootView);
    }


    /**
     * 指定选中的时间，显示选择器
     *
     * @param parent
     * @param gravity
     * @param x
     * @param y
     * @param date
     */
    public void showAtLocation(View parent, int gravity, int x, int y) {
//        Calendar calendar = Calendar.getInstance();
//        if (date == null)
//            calendar.setTimeInMillis(System.currentTimeMillis());
//        else
//            calendar.setTime(date);
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int hours = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
////        wheelTime.setPicker(year, month, day, hours, minute);
        update();
        super.showAtLocation(parent, gravity, x, y);
    }
    public void show(View parent,int type){

        switch (type){

            case TYPE_AREA :

                mArea.setVisibility(View.VISIBLE);
                mProvince.setVisibility(View.GONE);
                mCity.setVisibility(View.GONE);
                break;
            case TYPE_CITY :

                mArea.setVisibility(View.GONE);
                mProvince.setVisibility(View.GONE);
                mCity.setVisibility(View.VISIBLE);

                break;
            case TYPE_PROVINCE :

                mArea.setVisibility(View.GONE);
                mProvince.setVisibility(View.VISIBLE);
                mCity.setVisibility(View.GONE);

                break;

        }
        this.setAnimationStyle(R.style.popwindow_no_anim_style);
        showAtLocation(parent, Gravity.CENTER,0,0);
    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
            return;
        } else {
            if (citySelectListener != null) {
                String city = wheelCity.getResult();
                citySelectListener.onCitySelect(city);
            }
            dismiss();
            return;
        }
    }

    public interface OnCitySelectListener {
        public void onCitySelect(String city);
    }

    public void setOnCitySelectListener(OnCitySelectListener timeSelectListener) {
        this.citySelectListener = timeSelectListener;
    }

}
