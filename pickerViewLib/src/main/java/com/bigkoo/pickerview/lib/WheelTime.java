package com.bigkoo.pickerview.lib;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.R;
import com.bigkoo.pickerview.TimePopupWindow.Type;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


public class WheelTime {
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static DateFormat dateNewFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
    private View view;
    private WheelView mWheelYear;
    private WheelView mWheelMonth;
    private WheelView mWheelDay;
    private WheelView mWheelHour;
    private WheelView mWheelMin;
    public int mScreenHeight;

    public static final String FORMAT_YEAR = "%s年";
    public static final String FORMAT_MONTH = "%s月";
    public static final String FORMAT_DAY = "%s日";
    public static final String FORMAT_HOUR = "%s时";
    public static final String FORMAT_MINUNE = "%s分";

    /**
     * 当前年月日时分
     **/
    private int mCurrentYear;
    private int mCurrentMonth;
    private int mCurrentDay;
    private int mCurrentHour;
    private int mCurrentMin;

    private Type type;
    private static int START_YEAR = 1900, END_YEAR = 2100;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public static int getSTART_YEAR() {
        return START_YEAR;
    }

    public static void setSTART_YEAR(int sTART_YEAR) {
        START_YEAR = sTART_YEAR;
    }

    public static int getEND_YEAR() {
        return END_YEAR;
    }

    public static void setEND_YEAR(int eND_YEAR) {
        END_YEAR = eND_YEAR;
    }

    public WheelTime(View view) {
        super();
        this.view = view;
        type = Type.ALL;
        setView(view);
    }

    public WheelTime(View view, Type type) {
        super();
        this.view = view;
        this.type = type;
        setView(view);
    }

    public void setPicker(int year, int month, int day) {
        this.setPicker(year, month, day, 0, 0);
    }

    /**
     * 弹出日期时间选择器
     */
    public void setPicker(int year, int month, int day, int h, int m) {

        this.mCurrentYear = year;
        this.mCurrentMonth = month;
        this.mCurrentDay = day;
        this.mCurrentHour = h;
        this.mCurrentMin = m;

        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        Context context = view.getContext();
        // 年
        mWheelYear = (WheelView) view.findViewById(R.id.year);
        mWheelYear.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR, FORMAT_YEAR));// 设置"年"的显示数据
        mWheelYear.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

        // 月
        mWheelMonth = (WheelView) view.findViewById(R.id.month);
        mWheelMonth.setAdapter(new NumericWheelAdapter(1, 12, FORMAT_MONTH));
        mWheelMonth.setCurrentItem(month);

        // 日
        mWheelDay = (WheelView) view.findViewById(R.id.day);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (list_big.contains(String.valueOf(month))) {
            mWheelDay.setAdapter(new NumericWheelAdapter(1, 31, FORMAT_DAY));
        } else if (list_little.contains(String.valueOf(month + 1))) {
            mWheelDay.setAdapter(new NumericWheelAdapter(1, 30, FORMAT_DAY));
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                mWheelDay.setAdapter(new NumericWheelAdapter(1, 29, FORMAT_DAY));
            else
                mWheelDay.setAdapter(new NumericWheelAdapter(1, 28, FORMAT_DAY));
        }
        mWheelDay.setCurrentItem(day - 1);

        mWheelHour = (WheelView) view.findViewById(R.id.hour);
        mWheelHour.setAdapter(new NumericWheelAdapter(0, 23, FORMAT_HOUR));
        mWheelHour.setCurrentItem(h - 1);

        mWheelMin = (WheelView) view.findViewById(R.id.min);
        mWheelMin.setAdapter(new NumericWheelAdapter(0, 59, FORMAT_MINUNE));
        mWheelMin.setCurrentItem(m);

        // 添加"年"监听
        OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int year_num = newValue + START_YEAR;
                // 判断大小月及是否闰年,用来确定"日"的数据
                int maxItem = 30;
                if (list_big
                        .contains(String.valueOf(mWheelMonth.getCurrentItem() + 1))) {
                    mWheelDay.setAdapter(new NumericWheelAdapter(1, 31, FORMAT_DAY));
                    maxItem = 31;
                } else if (list_little.contains(String.valueOf(mWheelMonth
                        .getCurrentItem() + 1))) {
                    mWheelDay.setAdapter(new NumericWheelAdapter(1, 30, FORMAT_DAY));
                    maxItem = 30;
                } else {
                    if ((year_num % 4 == 0 && year_num % 100 != 0)
                            || year_num % 400 == 0) {
                        mWheelDay.setAdapter(new NumericWheelAdapter(1, 29, FORMAT_DAY));
                        maxItem = 29;
                    } else {
                        mWheelDay.setAdapter(new NumericWheelAdapter(1, 28, FORMAT_DAY));
                        maxItem = 28;
                    }
                }
                if (mWheelDay.getCurrentItem() > maxItem - 1) {
                    mWheelDay.setCurrentItem(maxItem - 1);
                }
            }
        };
        // 添加"月"监听
        OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int month_num = newValue + 1;
                int maxItem = 30;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (list_big.contains(String.valueOf(month_num))) {
                    mWheelDay.setAdapter(new NumericWheelAdapter(1, 31, FORMAT_DAY));
                    maxItem = 31;
                } else if (list_little.contains(String.valueOf(month_num))) {
                    mWheelDay.setAdapter(new NumericWheelAdapter(1, 30, FORMAT_DAY));
                    maxItem = 30;
                } else {
                    if (((mWheelYear.getCurrentItem() + START_YEAR) % 4 == 0 && (mWheelYear
                            .getCurrentItem() + START_YEAR) % 100 != 0)
                            || (mWheelYear.getCurrentItem() + START_YEAR) % 400 == 0) {
                        mWheelDay.setAdapter(new NumericWheelAdapter(1, 29, FORMAT_DAY));
                        maxItem = 29;
                    } else {
                        mWheelDay.setAdapter(new NumericWheelAdapter(1, 28, FORMAT_DAY));
                        maxItem = 28;
                    }
                }
                if (mWheelDay.getCurrentItem() > maxItem - 1) {
                    mWheelDay.setCurrentItem(maxItem - 1);
                }

            }
        };
        mWheelYear.addChangingListener(wheelListener_year);
        mWheelMonth.addChangingListener(wheelListener_month);

        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = 0;
        switch (type) {
            case ALL:
                textSize = (int) ((mScreenHeight / 100) * 3);
                break;
            case YEAR_MONTH_DAY:
                textSize = (mScreenHeight / 100) * 4;
                mWheelHour.setVisibility(View.GONE);
                mWheelMin.setVisibility(View.GONE);
                break;
            case HOURS_MINS:
                textSize = (mScreenHeight / 100) * 4;
                mWheelYear.setVisibility(View.GONE);
                mWheelMonth.setVisibility(View.GONE);
                mWheelDay.setVisibility(View.GONE);
                break;
            case MONTH_DAY_HOUR_MIN:
                textSize = (mScreenHeight / 100) * 3;
                mWheelYear.setVisibility(View.GONE);
                break;
        }

        mWheelDay.TEXT_SIZE = textSize;
        mWheelMonth.TEXT_SIZE = textSize;
        mWheelYear.TEXT_SIZE = textSize;
        mWheelHour.TEXT_SIZE = textSize;
        mWheelMin.TEXT_SIZE = textSize;

    }

    public void setPicker(int year, int month, int day, int h, int m, boolean flag) {

        this.mCurrentYear = year;
        this.mCurrentMonth = month;
        this.mCurrentDay = day;
        this.mCurrentHour = h;
        this.mCurrentMin = m;

        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        final List<String> bigMonth = Arrays.asList(months_big);
        final List<String> littleMonth = Arrays.asList(months_little);

        // 年
        mWheelYear = (WheelView) view.findViewById(R.id.year);
        mWheelYear.setAdapter(new NumericWheelAdapter(year, END_YEAR, FORMAT_YEAR));// 设置"年"的显示数据
        mWheelYear.setCurrentItem(0);// 初始化时显示的数据

        // 月
        mWheelMonth = (WheelView) view.findViewById(R.id.month);
        mWheelMonth.setAdapter(new NumericWheelAdapter(month, 12, FORMAT_MONTH));
        mWheelMonth.setCurrentItem(0);

        // 日
        mWheelDay = (WheelView) view.findViewById(R.id.day);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (bigMonth.contains(String.valueOf(month + 1))) {
            mWheelDay.setAdapter(new NumericWheelAdapter(day, 31, FORMAT_DAY));
        } else if (littleMonth.contains(String.valueOf(month + 1))) {
            mWheelDay.setAdapter(new NumericWheelAdapter(day, 30, FORMAT_DAY));
        } else {
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                mWheelDay.setAdapter(new NumericWheelAdapter(day, 29, FORMAT_DAY));
            else
                mWheelDay.setAdapter(new NumericWheelAdapter(day, 28, FORMAT_DAY));
        }
        mWheelDay.setCurrentItem(0);

        mWheelHour = (WheelView) view.findViewById(R.id.hour);
        mWheelHour.setAdapter(new NumericWheelAdapter(h, 23, FORMAT_HOUR));
        mWheelHour.setCurrentItem(0);

        mWheelMin = (WheelView) view.findViewById(R.id.min);
        mWheelMin.setAdapter(new NumericWheelAdapter(m, 59, FORMAT_MINUNE));
        mWheelMin.setCurrentItem(0);

        // 添加"年"监听
        mWheelYear.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int yearNum = newValue + START_YEAR;
                // 判断大小月及是否闰年,用来确定"日"的数据
                int monthMaxDay;
                if (bigMonth
                        .contains(String.valueOf(mWheelMonth.getCurrentItem() + 1))) {
                    monthMaxDay = 31;
                } else if (littleMonth.contains(String.valueOf(mWheelMonth
                        .getCurrentItem()))) {
                    monthMaxDay = 30;
                } else {
                    if ((yearNum % 4 == 0 && yearNum % 100 != 0)
                            || yearNum % 400 == 0) {
                        monthMaxDay = 29;
                    } else {
                        monthMaxDay = 28;
                    }
                }

                mWheelDay.setAdapter(new NumericWheelAdapter(1, monthMaxDay, FORMAT_DAY));
                if (mWheelDay.getCurrentItem() > monthMaxDay - 1) {
                    mWheelDay.setCurrentItem(monthMaxDay - 1);
                }

                checkMonth();
                checkDay(monthMaxDay);
                checkHour();
                checkMin();
            }
        });

        mWheelMonth.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int month_num = Integer.parseInt(mWheelMonth.getCurrentValue().replace("月",""));
                int maxItem = 30;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (bigMonth.contains(String.valueOf(month_num))) {
                    maxItem = 31;
                } else if (littleMonth.contains(String.valueOf(month_num))) {
                    maxItem = 30;
                } else {
                    if (((mWheelYear.getCurrentItem() + START_YEAR) % 4 == 0 && (mWheelYear
                            .getCurrentItem() + START_YEAR) % 100 != 0)
                            || (mWheelYear.getCurrentItem() + START_YEAR) % 400 == 0) {
                        maxItem = 29;
                    } else {
                        maxItem = 28;
                    }
                }
                mWheelDay.setAdapter(new NumericWheelAdapter(1, maxItem, FORMAT_DAY));
                if (mWheelDay.getCurrentItem() > maxItem - 1) {
                    mWheelDay.setCurrentItem(maxItem - 1);
                }

                checkDay(maxItem);
                checkHour();
                checkMin();
            }
        });

        mWheelDay.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                checkHour();
                checkMin();
            }
        });

        mWheelHour.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                checkMin();
            }
        });

        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = 0;
        switch (type) {
            case ALL:
                textSize = (mScreenHeight / 100) * 3;
                break;
            case YEAR_MONTH_DAY:
                textSize = (mScreenHeight / 100) * 4;
                mWheelHour.setVisibility(View.GONE);
                mWheelMin.setVisibility(View.GONE);
                break;
            case HOURS_MINS:
                textSize = (mScreenHeight / 100) * 4;
                mWheelYear.setVisibility(View.GONE);
                mWheelMonth.setVisibility(View.GONE);
                mWheelDay.setVisibility(View.GONE);
                break;
            case MONTH_DAY_HOUR_MIN:
                textSize = (mScreenHeight / 100) * 3;
                mWheelYear.setVisibility(View.GONE);
                break;
        }

        mWheelDay.TEXT_SIZE = textSize;
        mWheelMonth.TEXT_SIZE = textSize;
        mWheelYear.TEXT_SIZE = textSize;
        mWheelHour.TEXT_SIZE = textSize;
        mWheelMin.TEXT_SIZE = textSize;

    }

    private void checkMonth(){
        int month = 1;
        if (mWheelYear.getCurrentItem() == 0){
            month = mCurrentMonth;
        }
        mWheelMonth.setAdapter(new NumericWheelAdapter(month, 12, FORMAT_MONTH));
        if (mWheelMonth.getCurrentItem() > mWheelMonth.getCount()){
            mWheelMonth.setCurrentItem(0);
        }
    }

    private void checkDay(int max){
        int day = 1;
        if (mWheelMonth.getCurrentItem() == 0 && mWheelYear.getCurrentItem() == 0){
            day = mCurrentDay;
        }
        mWheelDay.setAdapter(new NumericWheelAdapter(day, max, FORMAT_DAY));
        if (mWheelDay.getCurrentItem() > mWheelDay.getCount()){
            mWheelDay.setCurrentItem(0);
        }
    }

    private void checkHour(){
        int hour = 0;
        if (mWheelDay.getCurrentItem() == 0 && mWheelMonth.getCurrentItem() == 0){
            hour = mCurrentHour;
        }
        mWheelHour.setAdapter(new NumericWheelAdapter(hour, 23, FORMAT_HOUR));
        if (mWheelHour.getCurrentItem() >= mWheelHour.getCount()){
            mWheelHour.setCurrentItem(0);
        }
    }


    private void checkMin(){
        int min = 0;
        if (mWheelHour.getCurrentItem() == 0 && mWheelDay.getCurrentItem() == 0){
            min = mCurrentMin;
        }
        mWheelMin.setAdapter(new NumericWheelAdapter(min, 59, FORMAT_MINUNE));
        if (mWheelMin.getCurrentItem() >= mWheelMin.getCount()){
            mWheelMin.setCurrentItem(0);
        }
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        mWheelYear.setCyclic(cyclic);
        mWheelMonth.setCyclic(cyclic);
        mWheelDay.setCyclic(cyclic);
        mWheelHour.setCyclic(cyclic);
        mWheelMin.setCyclic(cyclic);
    }

    public String getTime() {
        StringBuffer sb = new StringBuffer();
        sb.append((mWheelYear.getCurrentItem() + START_YEAR)).append("-")
                .append((mWheelMonth.getCurrentItem() + 1)).append("-")
                .append((mWheelDay.getCurrentItem() + 1)).append(" ")
                .append(mWheelHour.getCurrentItem()).append(":")
                .append(mWheelMin.getCurrentItem());
        return sb.toString();
    }

    public String getNewTime(){
        StringBuffer sb = new StringBuffer();
        sb.append(mWheelYear.getCurrentValue())
                .append(mWheelMonth.getCurrentValue())
                .append(mWheelDay.getCurrentValue())
                .append(mWheelHour.getCurrentValue())
                .append(mWheelMin.getCurrentValue());
        return sb.toString();
    }
}
