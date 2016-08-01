package com.binvshe.binvshe.binvsheui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by
 * zhangqianqian  禁止ViewPager向右滑动
 * on 2016/5/23.
 */
public class CanotSlidingViewpager extends ViewPager {

    private float before;

    public CanotSlidingViewpager(Context context) {
        super(context);
    }

    public CanotSlidingViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                before = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                final float move = ev.getX() - before;
                if (move < 0) { //修改判断可以修改控制禁止滑动方向
                    return true;
                } else {
                    before = ev.getX();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
