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

import org.srr.dev.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/3
 */
public class IndentSureActivity extends BaseActivity {

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

    public static void start(Context context) {
        Intent starter = new Intent(context, IndentSureActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.act_sure_indent;
    }

    @Override
    public void initView() {
        mTvActTime.setText(Html.fromHtml(getResources().getString(R.string.sure_indent_time, "2000000")));
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_title_back, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_title_back:
                this.finish();
                break;
            case R.id.btnCommit:
                final TipDialog tipDialog=TipDialog.newInstance("是否取消订单","继续购买","是");
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
                tipDialog.show(getFragmentManager(),"tag");
                break;
        }
    }
}
