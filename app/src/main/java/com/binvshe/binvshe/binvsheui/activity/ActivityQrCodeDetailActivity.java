package com.binvshe.binvshe.binvsheui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.binvshe.binvshe.R;

import org.srr.dev.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ActivityQrCodeDetailActivity extends BaseActivity {


    @Bind(R.id.tv_title)
    TextView mTvTitle;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityQrCodeDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.qr_code_detail_layout;
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

    @OnClick(R.id.iv_title_left)
    public void onClick() {
    }



}
