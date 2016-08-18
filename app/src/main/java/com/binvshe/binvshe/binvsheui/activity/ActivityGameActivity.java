package com.binvshe.binvshe.binvsheui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.home.GamePopupwindow;
import com.sdsmdg.tastytoast.TastyToast;

import org.srr.dev.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/18
 */
public class ActivityGameActivity extends BaseActivity {
    @Bind(R.id.ivActivityImage)
    ImageView mIvActivityImage;
    @Bind(R.id.tvActivityTitle)
    TextView mTvActivityTitle;
    @Bind(R.id.tvActivityTime)
    TextView mTvActivityTime;
    @Bind(R.id.btnUpload)
    TextView mBtnUpload;
    @Bind(R.id.wvActivityContent)
    WebView mWvActivityContent;
    @Bind(R.id.btn_title_back)
    ImageView mBtnTitleBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_title_more)
    ImageView mIvTitleMore;
    @Bind(R.id.tv_title_more)
    TextView mTvTitleMore;
    @Bind(R.id.rl_ac_navi_title)
    View mRlBackTitle;


    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityGameActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void initGetIntent() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_activity_game;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        mRlBackTitle.setAlpha(0f);
        mIvTitleMore.setVisibility(View.VISIBLE);
        mIvTitleMore.setImageResource(R.mipmap.icon_share);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshData() {

    }

    @OnClick({R.id.btnUpload, R.id.btn_title_back, R.id.iv_title_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUpload:
                GamePopupwindow popupwindow=new GamePopupwindow(this,view);
                popupwindow.show();
                break;
            case R.id.btn_title_back:
                this.finish();
                break;
            case R.id.iv_title_more:
                TastyToast.makeText(this,"share",TastyToast.LENGTH_SHORT,TastyToast.DEFAULT);
                break;
        }
    }
}
