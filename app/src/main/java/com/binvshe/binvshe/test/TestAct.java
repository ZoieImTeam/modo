package com.binvshe.binvshe.test;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import com.binvshe.binvshe.R;

import org.srr.dev.base.BaseActivity;
import org.srr.dev.util.UIL;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/6/15
 */
public class TestAct extends BaseActivity {
    @Bind(R.id.iv_test)
    ImageView ivTest;

    String url="file:///storage/sdcard0/Tencent/QQfile_recv/6.乌鸦嘴.jpg";
    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.act_test;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        Log.d("TestAct", url);
        UIL.load(ivTest,url,this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshData() {

    }


}
