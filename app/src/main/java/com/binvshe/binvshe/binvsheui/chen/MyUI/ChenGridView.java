package com.binvshe.binvshe.binvsheui.chen.MyUI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by chenjy on 2015/11/26.
 */
public class ChenGridView extends GridView {


    public ChenGridView(Context context) {
        super(context);
    }

    public ChenGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChenGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
