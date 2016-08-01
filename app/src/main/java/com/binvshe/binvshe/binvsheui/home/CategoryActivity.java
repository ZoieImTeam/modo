package com.binvshe.binvshe.binvsheui.home;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;
import com.binvshe.binvshe.entity.channel.CatetoryItem;
import com.binvshe.binvshe.entity.channel.HotRes;
import com.binvshe.binvshe.http.model.GetChannelHotModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.ChannelHotResponse;

import org.srr.dev.adapter.ViewPagerAdapter;
import org.srr.dev.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AbsFragmentActivity implements IViewModelInterface {

    public static final String CHANNEL_TYPE = "com.binvshe.binvshe.category.id";

    private List<String> mDatas = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private PagerSlidingTabStrip indicator;
    private ViewPager mPager;
    private ViewPagerAdapter mPagerAdapter;
    private GetChannelHotModel model;
    private String mTypeId;

    @Override
    protected void initGetIntent() {
        mTypeId = getIntent().getStringExtra(CHANNEL_TYPE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_category;
    }

    @Override
    public void initView() {
        indicator = findView(R.id.psts_ac_category_indicator);
        mPager = findView(R.id.vp_ac_category);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragments, mDatas);
    }

    @Override
    public void initData() {
        model = new GetChannelHotModel();
        model.setViewModelInterface(this);
        model.start(mTypeId);
    }

    @Override
    public void onClickView(View view, int id) {
    }

    @Override
    public void refreshData() {

    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {
        showLoadingDialog();
    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        dismissLoadingDialog();
        if (tag == model.getTag()) {
            ChannelHotResponse response = (ChannelHotResponse) result;
            HotRes data = response.getData();

            mDatas.clear();
            mFragments.clear();
            mDatas.add("推荐");
            mFragments.add(new ChannelHotFragment(data.getList()));
            for (CatetoryItem item : data.getType()) {
                mFragments.add(new ChannelItemFragment(item.getId() + ""));
                mDatas.add(item.getName());
            }
            mPager.setAdapter(mPagerAdapter);
            indicator.setViewPager(mPager);
        }
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        dismissLoadingDialog();
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        dismissLoadingDialog();
    }

    public void onPullRefresh() {
        model.start("1");
    }
}
