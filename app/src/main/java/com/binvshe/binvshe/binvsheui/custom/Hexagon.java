package com.binvshe.binvshe.binvsheui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.chen.Utils.ChenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjy on 2016/1/27.
 */
public class Hexagon extends ViewGroup {

    private List<PointXY> listPoint = new ArrayList<>();
    private List<PointXY> listMax = new ArrayList<>();
    private List<PointXY> listXY = new ArrayList<>();
    private List<PointXY> listHalf = new ArrayList<>();

    Paint paint_line;
    Paint paint_ps;
    private int center_x;
    private int center_y;

    public Hexagon(Context context) {
        super(context);
    }

    public Hexagon(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint_line = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_line.setColor(getResources().getColor(R.color.theme_color_blue));
        paint_line.setStrokeWidth(3);
        paint_line.setStyle(Paint.Style.STROKE);

        paint_ps = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_ps.setColor(getResources().getColor(R.color.theme_color_blue_tt));
        paint_ps.setStyle(Paint.Style.FILL);
    }

    public Hexagon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
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
        for (int i = 0; i < listPoint.size(); i++) {
            PointXY startPoint = listPoint.get(i);
            PointXY endPoint = null;
            if (i == listPoint.size() - 1) {
                endPoint = listPoint.get(0);
            } else {
                endPoint = listPoint.get(i + 1);
            }
            canvas.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY(), paint_line);
            canvas.drawLine(startPoint.getX(), startPoint.getY(), cPoint.getX(), cPoint.getY(), paint_line);
        }

        // 画最大值的线
        Path pMax = new Path();
        pMax.moveTo(listMax.get(0).getX(), listMax.get(0).getY());
        pMax.lineTo(listMax.get(1).getX(), listMax.get(1).getY());
        pMax.lineTo(listMax.get(2).getX(), listMax.get(2).getY());
        pMax.lineTo(listMax.get(3).getX(), listMax.get(3).getY());
        pMax.lineTo(listMax.get(4).getX(), listMax.get(4).getY());
        pMax.lineTo(listMax.get(5).getX(), listMax.get(5).getY());
        pMax.close();//封闭
        canvas.drawPath(pMax, paint_line);

        //绘制虚线
        Paint paint_xuline = new Paint();
        paint_xuline.setStyle(Paint.Style.STROKE);
        paint_xuline.setColor(getResources().getColor(R.color.theme_color_blue));
        paint_xuline.setStrokeWidth(3);
        Path path = new Path();
        path.moveTo(listHalf.get(0).getX(), listHalf.get(0).getY());
        path.lineTo(listHalf.get(1).getX(), listHalf.get(1).getY());
        path.lineTo(listHalf.get(2).getX(), listHalf.get(2).getY());
        path.lineTo(listHalf.get(3).getX(), listHalf.get(3).getY());
        path.lineTo(listHalf.get(4).getX(), listHalf.get(4).getY());
        path.lineTo(listHalf.get(5).getX(), listHalf.get(5).getY());
        path.close();//封闭
        PathEffect effects = new DashPathEffect(new float[]{15, 15, 15, 15}, 1);
        paint_xuline.setPathEffect(effects);
        canvas.drawPath(path, paint_xuline);

        // 你可以绘制很多任意多边形，比如下面画六连形
        if (listXY.size() != 0) {
            Path path1 = new Path();
            path1.moveTo(listXY.get(0).getX(), listXY.get(0).getY());
            path1.lineTo(listXY.get(1).getX(), listXY.get(1).getY());
            path1.lineTo(listXY.get(2).getX(), listXY.get(2).getY());
            path1.lineTo(listXY.get(3).getX(), listXY.get(3).getY());
            path1.lineTo(listXY.get(4).getX(), listXY.get(4).getY());
            path1.lineTo(listXY.get(5).getX(), listXY.get(5).getY());
            path1.close();//封闭
            canvas.drawPath(path1, paint_ps);
        }
    }

    public void setNumber(int[] nums) {
        double max = ChenUtils.getMAX(nums);
        listXY.clear();
        for (int i = 0; i < nums.length; i++) {
            double p = (double) nums[i] / max;
            int x1 = (int) (((listMax.get(i).getX() - center_x) * p) + center_x);
            int y1 = (int) (((listMax.get(i).getY() - center_y) * p) + center_y);
            PointXY pp = new PointXY(x1, y1);
            listXY.add(pp);
        }
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("onLayout", "onLayout");
        center_x = getWidth() / 2;
        center_y = getHeight() / 2;

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
                    ct = getHeight() / 4 - cHeight / 4 - cParams.bottomMargin;
                    break;

                case 2:
                    cl = getWidth() - cWidth - cParams.leftMargin
                            - cParams.rightMargin;
                    ct = getHeight() * 3 / 4 - cHeight * 3 / 4 - cParams.bottomMargin;
                    break;

                case 3:
                    cl = (getWidth() - cWidth) / 2;
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    break;

                case 4:
                    cl = cParams.leftMargin;
                    ct = getHeight() * 3 / 4 - cHeight * 3 / 4 - cParams.bottomMargin;
                    break;
                case 5:
                    cl = cParams.leftMargin;
                    ct = getHeight() / 4 - cHeight / 4 - cParams.bottomMargin;
                    break;
            }
            cr = cl + cWidth;
            cb = cHeight + ct;
            childView.layout(cl, ct, cr, cb);

            int x = (cr + cl) / 2;
            int y = (cb + ct) / 2;
            PointXY pointXY = new PointXY(x, y);
            listPoint.add(pointXY);

            double p = (double) (center_y - 140) / (double) center_y;
            int x1 = (int) (((x - center_x) * p) + center_x);
            int y1 = (int) (((y - center_y) * p) + center_y);
            PointXY pMax = new PointXY(x1, y1);
            listMax.add(pMax);

            int x2 = (int) (((x - center_x) * p * 0.5) + center_x);
            int y2 = (int) (((y - center_y) * p * 0.5) + center_y);
            PointXY pHalf = new PointXY(x2, y2);
            listHalf.add(pHalf);
        }
    }

}
