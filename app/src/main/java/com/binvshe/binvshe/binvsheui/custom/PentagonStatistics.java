package com.binvshe.binvshe.binvsheui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.binvshe.binvshe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjy on 2016/1/27.
 */
public class PentagonStatistics extends ViewGroup {

    private List<PointXY> listPoint = new ArrayList<>();
    Paint paint_line;
    Paint paint_ps;

    public PentagonStatistics(Context context) {
        super(context);
    }

    public PentagonStatistics(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint_line = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_line.setColor(getResources().getColor(R.color.theme_color_blue));
        paint_line.setStrokeWidth(3);

        paint_ps = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_ps.setColor(getResources().getColor(R.color.theme_color_blue));
        paint_ps.setStyle(Paint.Style.FILL);
    }

    public PentagonStatistics(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("onMeasure", "onMeasure");
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 记录如果是wrap_content是设置的宽和高
         */
        int width = 0;
        int height = 0;

        int cCount = getChildCount();

        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        // 用于计算左边两个childView的高度
        int lHeight = 0;
        // 用于计算右边两个childView的高度，最终高度取二者之间大值
        int rHeight = 0;

        // 用于计算上边两个childView的宽度
        int tWidth = 0;
        // 用于计算下面两个childiew的宽度，最终宽度取二者之间大值
        int bWidth = 0;

        /**
         * 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
         */
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            // 上面两个childView
            if (i == 0 || i == 1) {
                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }

            if (i == 2 || i == 3) {
                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }

            if (i == 0 || i == 2) {
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }

            if (i == 1 || i == 3) {
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }

        }

        width = Math.max(tWidth, bWidth);
        height = Math.max(lHeight, rHeight);

        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
                : width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
                : height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("onDraw", "onDraw");
        PointXY cPoint = new PointXY(getWidth() / 2, getHeight() / 2);
        for (int i = 0; i < listPoint.size(); i ++){
            PointXY startPoint = listPoint.get(i);
            PointXY endPoint = null;
            if (i == listPoint.size() - 1){
                endPoint = listPoint.get(0);
            }else{
                endPoint = listPoint.get(i + 1);
            }
            canvas.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY(), paint_line);
            canvas.drawLine(startPoint.getX(), startPoint.getY(), cPoint.getX(), cPoint.getY(), paint_line);
        }

        // 你可以绘制很多任意多边形，比如下面画六连形
//        Path path1=new Path();
//        path1.moveTo(listPoint.get(0).getX(), listPoint.get(0).getY());
//        path1.lineTo(listPoint.get(1).getX(), listPoint.get(1).getY());
//        path1.lineTo(listPoint.get(2).getX(), listPoint.get(2).getY());
//        path1.lineTo(listPoint.get(3).getX(), listPoint.get(3).getY());
//        path1.lineTo(listPoint.get(4).getX(), listPoint.get(4).getY());
//        path1.close();//封闭
//        canvas.drawPath(path1, paint_ps);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("onLayout", "onLayout");
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;
        /**
         * 遍历所有childView根据其宽和高，以及margin进行布局
         */
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr = 0, cb = 0;

            switch (i) {
                case 0:
                    cl = (getWidth() - cWidth) / 2;
                    ct = cParams.topMargin;
                    break;

                case 1:
                    cl = getWidth() - cWidth - cParams.leftMargin
                            - cParams.rightMargin;
                    ct = cParams.topMargin + (getHeight() / 4);
                    break;

                case 2:
                    cl = getWidth() - cWidth - cParams.leftMargin
                            - cParams.rightMargin - (getWidth() / 6);
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    break;

                case 3:
                    cl = cParams.leftMargin + (getWidth() / 6);
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    break;

                case 4:
                    cl = cParams.leftMargin;
                    ct = cParams.topMargin + (getHeight() / 4);
                    break;
            }
            cr = cl + cWidth;
            cb = cHeight + ct;
            childView.layout(cl, ct, cr, cb);

            PointXY pointXY = new PointXY((cr + cl) / 2, (cb + ct) / 2);
            listPoint.add(pointXY);
            Log.e("pointxy",pointXY.toString());
        }
    }

    private class PointXY{

        private int x;
        private int y;

        public PointXY(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "PointXY{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
