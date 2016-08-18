package com.binvshe.binvshe.binvsheui.home;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.release.ReleaseGameActivity;


import butterknife.OnClick;


/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/7/6
 */
public class GamePopupwindow extends PopupWindow implements View.OnClickListener {

    TextView mBtnDrawGame;
    TextView mBtnClothGame;
    TextView mBtnSleepGame;
    Button mBtnClose;
    private Context mContext;
    private View mParentView;
    private View mContentView;


    public GamePopupwindow(Context context, View parentView) {
        this.mContext = context;
        this.mParentView = parentView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = inflater.inflate(R.layout.ppw_game, null);
        this.setContentView(mContentView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setAnimationStyle(R.style.popwindow_anima_style);
        this.setFocusable(true);
        this.setBackgroundDrawable(new ColorDrawable());
        initView(mContentView);
    }

    private void initView(View mContentView) {
        mBtnDrawGame= (TextView) mContentView.findViewById(R.id.btnDrawGame);
        mBtnClothGame= (TextView) mContentView.findViewById(R.id.btnClothGame);
        mBtnSleepGame= (TextView) mContentView.findViewById(R.id.btnSleepGame);
        mBtnClose= (Button) mContentView.findViewById(R.id.btnClose);
        mBtnDrawGame.setOnClickListener(this);
        mBtnClothGame.setOnClickListener(this);
        mBtnSleepGame.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);
    }

    public void show() {
        if (!this.isShowing()) {
            this.showAtLocation(mParentView, Gravity.CENTER, 0, 0);
        } else {
            this.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDrawGame:
                ReleaseGameActivity.start(mContext,"34");//萌都动漫大赛 id 34
                break;
            case R.id.btnClothGame:
                ReleaseGameActivity.start(mContext,"35");//萌都二次元服装设计大赛 id 35
                break;
            case R.id.btnSleepGame:
                break;
            case R.id.btnClose:
                this.dismiss();
                break;
        }
    }

}
