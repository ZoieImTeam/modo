package com.binvshe.binvshe.binvsheui.find;

import com.binvshe.binvshe.entity.dynamic.DynamicSpe;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/5/20
 */
public interface ContentCallBack {

    /**
     * 顶部渐变动画回调
     * @param scrollY
     * @param changeHeight
     */
     void changeTitleBackground(int scrollY,float changeHeight);

     void showItem();

     void dismissItem();

     void refresh(DynamicSpe spec);
     void addAttention(DynamicSpe spec);
     void cancelAttention(DynamicSpe spec);

}
