package com.binvshe.binvshe.binvsheui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.dialog.TipDialog;
import com.binvshe.binvshe.entity.ActivityList.CreateOrderEntity;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import org.srr.dev.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/3
 * 确认订单界面
 */
public class IndentSureActivity extends BaseActivity {

    private static final String KEY_ORDER_MSG = "ORDER_MSG";
    @Bind(R.id.btn_title_back)
    ImageView mBtnTitleBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tvActTitle)
    TextView mTvActTitle;
    @Bind(R.id.tvActTime)
    TextView mTvActTime;
    @Bind(R.id.btnCommit)
    TextView mBtnCommit;


    @InjectExtra(KEY_ORDER_MSG)
    CreateOrderEntity mOrderMsg;



    public static void start(Context context, CreateOrderEntity orderMsg) {
        Intent starter = new Intent(context, IndentSureActivity.class);
        starter.putExtra(KEY_ORDER_MSG,orderMsg);
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {
        Dart.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_sure_indent;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mTvActTime.setText(Html.fromHtml(getResources().getString(R.string.sure_indent_time, "2000000")));
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshData() {

    }


    @OnClick({R.id.btn_title_back, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_title_back:
                this.finish();
                break;
            case R.id.btnCommit:
                final TipDialog tipDialog = TipDialog.newInstance("是否取消订单", "继续购买", "是");
                tipDialog.setmOnClickable(new TipDialog.TipDialogClickable() {
                    @Override
                    public void btnSureCick() {
                        Toast.makeText(_this, "确定", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void btnCancleClick() {
                        tipDialog.dismiss();
                    }
                });
                tipDialog.show(getFragmentManager(), "tag");
                break;
        }
    }
}
