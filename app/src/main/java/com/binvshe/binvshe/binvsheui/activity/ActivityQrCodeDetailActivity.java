package com.binvshe.binvshe.binvsheui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import org.srr.dev.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ActivityQrCodeDetailActivity extends BaseActivity {
    private static final String KEY_ORDERID = "ORDERID";

    @Bind(R.id.tv_title)
    TextView mTvTitle;

    @InjectExtra(KEY_ORDERID)
    String mOrderID;

    public static void start(Context context,String orderID) {
        Intent starter = new Intent(context, ActivityQrCodeDetailActivity.class);
        starter.putExtra(KEY_ORDERID,orderID);
        context.startActivity(starter);
    }


    @Override
    protected void initGetIntent() {
        Dart.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.qr_code_detail_layout;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mTvTitle.setText("查看二维码");

    }

    @Override
    public void initData() {
        getSupportFragmentManager().beginTransaction().add(R.id.flyt_base,ActivityQrCodeDetailFragment.newInstance(mOrderID)).commit();
    }

    @Override
    public void refreshData() {

    }

    @OnClick(R.id.btn_title_back)
    public void onClick() {
        this.finish();
    }



}
