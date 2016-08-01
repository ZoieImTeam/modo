package com.binvshe.binvshe.binvsheui.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.AbsFragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AbsFragmentActivity {

    private List<Integer> listGuiImage = new ArrayList<>();

    @Override
    protected void initGetIntent() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        ViewPager page = findView(R.id.guide_page);
        page.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
    }

    @Override
    public void initData() {
        listGuiImage.add(R.mipmap.gui1);
        listGuiImage.add(R.mipmap.gui2);
        listGuiImage.add(R.mipmap.gui3);
        listGuiImage.add(R.mipmap.gui4);
    }

    @Override
    public void onClickView(View view, int id) {

    }

    @Override
    public void refreshData() {

    }

    private class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return GuideFragment.newInstance(position, listGuiImage.get(position));
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
