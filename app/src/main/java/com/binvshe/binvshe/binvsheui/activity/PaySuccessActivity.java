package com.binvshe.binvshe.binvsheui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.binvshe.binvshe.R;

import org.srr.dev.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/4
 * 支付成功界面
 */
public class PaySuccessActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_title_more)
    TextView mTvTitleMore;
    @Bind(R.id.tvPrice)
    TextView mTvPrice;
    @Bind(R.id.tvAcitvityContent)
    TextView mTvAcitvityContent;
    @Bind(R.id.btnShowCode)
    TextView mBtnShowCode;

    public static void start(Context context) {
        Intent starter = new Intent(context, PaySuccessActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.act_pay_success;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshData() {

    }



    @OnClick({R.id.tv_title_more, R.id.btnShowCode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_more:
                break;
            case R.id.btnShowCode:
                break;
        }
    }
}
