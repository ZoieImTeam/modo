package com.binvshe.binvshe.binvsheui.login;

import android.view.View;

import com.binvshe.binvshe.R;

import org.srr.dev.base.BaseActivity;

/**
 * Created by
 * zhangqianqian
 * on 2016/5/20.
 */
public class ChooseActivity extends BaseActivity {
    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_choose;
    }

    @Override
    public void initView() {
        findViewById(R.id.iv_ac_choose_weibo).setOnClickListener(this);
        findViewById(R.id.iv_ac_choose_weixin).setOnClickListener(this);
        findViewById(R.id.iv_ac_choose_qq).setOnClickListener(this);
        findViewById(R.id.tv_ac_choose_register).setOnClickListener(this);
        findViewById(R.id.tv_ac_choose_login).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ac_choose_login:
                LoginActivity.newInstance(this);
                break;
            case R.id.tv_ac_choose_register:
                RegisterActivity.newInstance(this,LoginActivity.FLAG_REGISTER);
                break;
            case R.id.iv_ac_choose_qq:
                break;
            case R.id.iv_ac_choose_weixin:
                break;
            case R.id.iv_ac_choose_weibo:
                break;
        }
    }

    @Override
    public void refreshData() {

    }
}
