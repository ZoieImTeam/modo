package com.bigkoo.pickerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.bigkoo.pickerview.lib.ArrayWheelAdapter;
import com.bigkoo.pickerview.lib.OnWheelChangedListener;
import com.bigkoo.pickerview.lib.ScreenInfo;
import com.bigkoo.pickerview.lib.WheelView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/28.
 */
public class StringArrayPopWindow extends PopupWindow implements View.OnClickListener {

    private View rootView; // 总的布局
    private WheelView mWheelView;
    private View btnSubmit, btnCancel;
    private OnWheelChangedListener mWheelChangedListener;
    private OnDismissListener onDismissListener;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";

    public StringArrayPopWindow(Context context) {
        super(context);
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new BitmapDrawable());// 这样设置才能点击屏幕外dismiss窗口
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.timepopwindow_anim_style);

        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        rootView = mLayoutInflater.inflate(R.layout.pw_stringarray, null);
        // -----确定和取消按钮
        btnSubmit = rootView.findViewById(R.id.btnSubmit_str);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = rootView.findViewById(R.id.pw_btn_cancel_str);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        // ----转轮
        mWheelView = (WheelView) rootView.findViewById(R.id.optionspicker_str);
        ScreenInfo screenInfo = new ScreenInfo((Activity) context);
        mWheelView.TEXT_SIZE = (screenInfo.getHeight() / 100) * 4;
        setContentView(rootView);
    }
    public StringArrayPopWindow(Context context ,String label){
        this(context);
        mWheelView.setLabel(label);
    }

    public void setStringArrayData(ArrayList<String> array){

        mWheelView.setAdapter(new ArrayWheelAdapter<String>(array));

    }

    public void setNumberData(int low, int high) {
        ArrayList<String> numOptions = new ArrayList<String>();
        if (low > high) {
            throw new IllegalArgumentException("low value should lower than high value");
        }
        for (int i = low; i <= high; i++) {
            numOptions.add(String.valueOf(i));
        }
        mWheelView.setAdapter(new ArrayWheelAdapter<String>(numOptions));
    }

    public OnWheelChangedListener getmWheelChangedListener() {
        return mWheelChangedListener;
    }

    public void setmWheelChangedListener(OnWheelChangedListener mWheelChangedListener) {
        this.mWheelChangedListener = mWheelChangedListener;
        mWheelView.addChangingListener(mWheelChangedListener);
    }

    public void setOnDismissListener(OnDismissListener listener){
        onDismissListener = listener;

    }
    @Override
    public void onClick(View view) {
        String tag=(String) view.getTag();
        if(tag.equals(TAG_CANCEL))
        {
            dismiss();
            if(onDismissListener != null){
                onDismissListener.onDismiss();
            }
            return;
        }
        else
        {

            dismiss();
            if(onDismissListener != null){
                onDismissListener.onDismiss();
            }
            return;
        }
    }
}
