package com.binvshe.binvshe.binvsheui.chen.MyUI;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by chenjy on 2015/11/26.
 */
public class ChenListView extends ListView {
    public ChenListView(Context context) {
        super(context);
    }

    public ChenListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChenListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
