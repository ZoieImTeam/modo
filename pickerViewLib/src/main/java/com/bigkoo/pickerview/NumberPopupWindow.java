package com.bigkoo.pickerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.bigkoo.pickerview.lib.NumericWheelAdapter;
import com.bigkoo.pickerview.lib.ScreenInfo;
import com.bigkoo.pickerview.lib.WheelView;

/**
 * Created by Zheng.wf on 2015/9/8.
 * Contact : 670100084@qq.com
 */
public class NumberPopupWindow extends PopupWindow implements View.OnClickListener {

    private View rootView; // 总的布局
    private View btnSubmit, btnCancel;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";

    private WheelView mWheelView;
    private NumPickerListener mNumPickerListener;

    public NumberPopupWindow(Context context) {
        super(context);
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new BitmapDrawable());// 这样设置才能点击屏幕外dismiss窗口
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.timepopwindow_anim_style);

        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        rootView = mLayoutInflater.inflate(R.layout.pw_number, null);
        // -----确定和取消按钮
        btnSubmit = rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = rootView.findViewById(R.id.pw_btn_cancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        // ----转轮
        View optionspicker = rootView.findViewById(R.id.optionspicker);

        mWheelView = (WheelView) optionspicker.findViewById(R.id.options1);
        setContentView(rootView);

        ScreenInfo screenInfo  = new ScreenInfo((Activity) context);
        mWheelView.TEXT_SIZE = screenInfo.getHeight() / 100 * 4;
        mWheelView.setVisibleItems(5);
    }

    public NumberPopupWindow(Context context, String label) {
        this(context);
        mWheelView.setLabel(label);
    }

    public void setNumberData(int low, int high) {
        if (low > high) {
            throw new IllegalArgumentException("low value should lower than high value");
        }
        mWheelView.setAdapter(new NumericWheelAdapter(low, high));
        mWheelView.setCurrentItem(0);
    }

    public void setNumPickerListener(NumPickerListener numPickerListener){
        this.mNumPickerListener = numPickerListener;
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_SUBMIT)) {
            if (mNumPickerListener != null) {
                mNumPickerListener.onNumberPick(mWheelView.getCurrentValue(), mWheelView.getCurrentItem());
            }
        }
        dismiss();
    }

    public interface NumPickerListener{
        void onNumberPick(String value, int index);
    }
}
