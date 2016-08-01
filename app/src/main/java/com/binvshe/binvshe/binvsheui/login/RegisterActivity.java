package com.binvshe.binvshe.binvsheui.login;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.chen.Utils.Constants;
import com.binvshe.binvshe.binvsheui.dialog.TipsDialog;
import com.binvshe.binvshe.binvsheui.widget.CanotSlidingViewpager;

import org.srr.dev.adapter.ViewPagerAdapter;
import org.srr.dev.base.BaseSwipeBackActivity;

import java.util.ArrayList;

import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseSwipeBackActivity {


    private CanotSlidingViewpager mViewPager;
    private int mType = LoginActivity.FLAG_REGISTER;
    private String phone;
    private RegisterTwoFr pageTwo;

    final ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (position!=1){
                pageTwo.timeChange(true);
            }
            setIsFull(position==0);  //设置第一页全屏返回，其他页边缘返回
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private ArrayList<Fragment> fragments;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void newInstance(Activity ac, int type) {
        Intent intent = new Intent(ac, RegisterActivity.class);
        intent.putExtra(LoginActivity.EXTRA_TYPE_SET_PASSWORD, type);
        ac.startActivity(intent);
    }


    @Override
    protected void initGetIntent() {
        SMSSDK.initSDK(this, Constants.SMS_KEY, Constants.SMS_SECRET);
        mType = getIntent().getExtras().getInt(LoginActivity.EXTRA_TYPE_SET_PASSWORD);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_register;
    }

    @Override
    public void initView() {
        mViewPager = (CanotSlidingViewpager) findViewById(R.id.vp_ac_register);
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        fragments = new ArrayList<>();
        ArrayList<String> pages = new ArrayList<>();
        fragments.add(RegisterOneFr.newInstance(mType));
        pageTwo = RegisterTwoFr.newInstance();
        fragments.add(pageTwo);
        fragments.add(RegisterThreeFr.newInstance(mType));
        pages.add("");
        pages.add("");
        pages.add("");
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments, pages));
        setIsFull(true);
    }

    @Override
    public void initData() {
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void onBackPressed() {
        if (phone != null) {
            TipsDialog dialog = TipsDialog.newInstance("返回将放弃找回密码，确定返回？", "取消", "返回");
            dialog.setOnDialogLisetener(new TipsDialog.OnDialogLisetener() {
                @Override
                public void cancel() {
                }

                @Override
                public void center() {
                    scrollToFinishActivity();
                    finish();
                }
            });
            dialog.show(getSupportFragmentManager(),"tip");
        }else{
            scrollToFinishActivity();
            super.onBackPressed();
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
        pageTwo.setPhone(phone);
    }

    public String getPhone() {
        return phone;
    }

    public void nextPage() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }

    public void sendSms() {
        pageTwo.timeChange(false);
        pageTwo.sendSmsCode();
    }
}
