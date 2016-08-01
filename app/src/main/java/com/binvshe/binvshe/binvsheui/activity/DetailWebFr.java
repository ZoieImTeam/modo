package com.binvshe.binvshe.binvsheui.activity;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.BaseFragment;

public class DetailWebFr extends BaseFragment {


    private WebView web;

    public static DetailWebFr newInstance() {

        Bundle args = new Bundle();

        DetailWebFr fragment = new DetailWebFr();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fr_web;
    }

    @Override
    protected void initView(View contentView) {
        web = (WebView) contentView.findViewById(R.id.wv_fr_web);
        //自适应屏幕
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setSupportZoom(true);
        web.getSettings().setTextZoom(250);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onClickView(View view, int id) {

    }

    @Override
    public void doAfterReConnectNewWork() {

    }

    public void setHtml(String html) {
        web.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }
}
