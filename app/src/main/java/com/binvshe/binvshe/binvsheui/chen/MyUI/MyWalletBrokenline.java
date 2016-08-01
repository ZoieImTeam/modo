package com.binvshe.binvshe.binvsheui.chen.MyUI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.binvshe.binvshe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjy on 2015/11/29.
 */
public class MyWalletBrokenline extends View {

    private Paint paint_date;
    private Paint paint_point;
    private Paint paint_line;
    private Paint paint_line_top;
    private Paint paint_text;

    private float textsize = 30;
    private float r;

    private float textmar = 10;
    private float viewHieght;
    private float spacing;
    private float[] pointYs = new float[7];
    private int index = 6;

    public void setStrTop(String strTop) {
        this.strTop = strTop;
    }

    public void setMAXTop(String strTop) {
        this.strTop = strTop;
        invalidate();
    }

    public void setStrBom(String strBom) {
        this.strBom = strBom;
    }

    private String strTop = "00";
    private String strBom = "0";

    private List<PointCoordinate> listCoor = new ArrayList<PointCoordinate>();
    private float maxStrwidth;

    public void setDate1(float date1) {
        this.date1 = date1;
    }

    public void setDate2(float date2) {
        this.date2 = date2;
    }

    public void setDate3(float date3) {
        this.date3 = date3;
    }

    public void setDate4(float date4) {
        this.date4 = date4;
    }

    public void setDate5(float date5) {
        this.date5 = date5;
    }

    public void setDate6(float date6) {
        this.date6 = date6;
    }

    public void setDate7(float date7) {
        this.date7 = date7;
    }

    private float date1;
    private float date2;
    private float date3;
    private float date4;
    private float date5;
    private float date6;
    private float date7;


    public MyWalletBrokenline(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyWalletBrokenline);
        r = array.getDimension(R.styleable.MyWalletBrokenline_point_stroke_r, 10);
        int point_stroke_color = array.getColor(R.styleable.MyWalletBrokenline_point_stroke_color, Color.RED);
        float line_topbom_width = array.getDimension(R.styleable.MyWalletBrokenline_line_topbom_width, 1);
        int line_topbom_color = array.getColor(R.styleable.MyWalletBrokenline_line_topbom_color, Color.RED);
        float line_width = array.getDimension(R.styleable.MyWalletBrokenline_line_width, 3);
        int line_color = array.getColor(R.styleable.MyWalletBrokenline_line_color, Color.RED);
        textsize = array.getDimension(R.styleable.MyWalletBrokenline_text_size, 30);
        int textcolor = array.getColor(R.styleable.MyWalletBrokenline_text_color, Color.RED);
        array.recycle();
        //画圆
        paint_point = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_point.setColor(point_stroke_color);
//        paint_point.setStyle(Paint.Style.STROKE);
        paint_point.setStrokeWidth(2 * r);
        //画各个点连线
        paint_line = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_line.setColor(line_color);
        paint_line.setStrokeWidth(line_width);
        //画上线准线
        paint_line_top = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_line_top.setColor(line_topbom_color);
        paint_line_top.setStrokeWidth(line_topbom_width);
        //文字画笔
        paint_text = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_text.setColor(textcolor);
        paint_text.setTextSize(textsize);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取上下基数大小，以及差
        float top = Float.parseFloat(strTop);
        float bom = Float.parseFloat(strBom);
        float max = top>bom?top:bom;
        float min = top<bom?top:bom;
        float gap = max - min;

        //判断上线准线文字宽度，取最宽的为准
        maxStrwidth = paint_text.measureText(strTop)>paint_text.measureText(strBom)
                ?paint_text.measureText(strTop):paint_text.measureText(strBom);

        viewHieght = getHeight() - (4 * r);
        spacing = getWidth() / 8;
        float pointY1 = (1 - ((date1 - min) / gap)) * viewHieght + 2 * r;
        float pointY2 = (1 - ((date2 - min) / gap)) * viewHieght + 2 * r;
        float pointY3 = (1 - ((date3 - min) / gap)) * viewHieght + 2 * r;
        float pointY4 = (1 - ((date4 - min) / gap)) * viewHieght + 2 * r;
        float pointY5 = (1 - ((date5 - min) / gap)) * viewHieght + 2 * r;
        float pointY6 = (1 - ((date6 - min) / gap)) * viewHieght + 2 * r;
        float pointY7 = (1 - ((date7 - min) / gap)) * viewHieght + 2 * r;
        pointYs = new float[]{pointY1, pointY2, pointY3, pointY4, pointY5, pointY6, pointY7};
        //绘制文字
        drawText(canvas);
        //绘制上下准线
        drawTopline(canvas);
        //绘制点跟连线
        darwPointAndLine(canvas);
        //绘制虚线
        Paint paint_xuline = new Paint();
        paint_xuline.setStyle(Paint.Style.STROKE);
        paint_xuline.setColor(Color.WHITE);
        paint_xuline.setStrokeWidth(3);
        Path path = new Path();
        path.moveTo(listCoor.get(index).getX(), listCoor.get(index).getY());
        path.lineTo(listCoor.get(index).getX(), getHeight() - 2 * r);
        PathEffect effects = new DashPathEffect(new float[]{5,5,5,5},1);
        paint_xuline.setPathEffect(effects);
        canvas.drawPath(path, paint_xuline);
    }

    private void drawText(Canvas canvas) {
        canvas.drawText(strTop, getWidth() - spacing/4 - maxStrwidth, 2*r+textsize+textmar,paint_text);
        canvas.drawText(strBom, getWidth() - spacing/4 - maxStrwidth, getHeight() - 2*r - textmar,paint_text);
    }

    private void darwPointAndLine(Canvas canvas) {
        //添加圆点坐标到容器
        listCoor.clear();
        for (int i = 0; i < 7; i++) {
            listCoor.add(new PointCoordinate(spacing / 2 + (spacing * i), pointYs[i]));
        }
        //绘制圆点跟连线
        for (int i = 0; i < 7; i++) {
            if (index == i){
                canvas.drawCircle(listCoor.get(i).getX(), pointYs[i], 3*r/2, paint_point);
            }else{
                canvas.drawCircle(listCoor.get(i).getX(), pointYs[i], r, paint_point);
            }
            if (i != 0) {
                canvas.drawLine(listCoor.get(i - 1).getX(), listCoor.get(i - 1).getY(),
                        listCoor.get(i).getX(), listCoor.get(i).getY(), paint_line);
            }
        }
    }

    private void drawTopline(Canvas canvas) {
        canvas.drawLine(0, 2 * r, getWidth(), 2 * r, paint_line_top);
        canvas.drawLine(0, getHeight() - 2 * r, getWidth(), getHeight() - 2 * r, paint_line_top);
    }

    class PointCoordinate {
//        private static final long serialVersionUID = 1L; // 定义序列化ID

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        private float x;
        private float y;

        public PointCoordinate(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        index = (int) (x/getWidth()*8);
        if (index > 6){
            index = 6;
        }
        invalidate();
        if (l != null){
            l.setIndex(index);
        }
        return true;
    }

    private OnbrokenlineListener l;
    public void setOnBrokenlineListener(OnbrokenlineListener l){
        this.l = l;
    }
    public interface OnbrokenlineListener{
        void setIndex(int index);
    }

    public void setDateMoney(List<Float> list){
        date1 = list.get(6);
        date2 = list.get(5);
        date3 = list.get(4);
        date4 = list.get(3);
        date5 = list.get(2);
        date6 = list.get(1);
        date7 = list.get(0);
        invalidate();
    }
}
