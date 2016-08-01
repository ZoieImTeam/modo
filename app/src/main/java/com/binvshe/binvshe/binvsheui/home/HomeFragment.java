package com.binvshe.binvshe.binvsheui.home;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.base.BaseFragment;
import com.binvshe.binvshe.binvsheui.release.SelectTypeFragment;
import com.binvshe.binvshe.binvsheui.widget.sliding.SlidingTabLayout;
import com.binvshe.binvshe.entity.subject.SubjectTypeEntity;
import com.binvshe.binvshe.helper.SubjectTypeHelper;

import org.srr.dev.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    private View layout;
    private AppCompatActivity mActivity;
    private ViewPager mPager;

    private List<Fragment> listFragment;
    private List<String> listTitle;
    private ArrayList<SubjectTypeEntity> mList;

    private SelectTypeFragment mFragment;

    public HomeFragment() {
    }

    @Override
    public int getLayoutId() {
        mActivity = (AppCompatActivity) getActivity();
        return R.layout.fr_home;
    }

    @Override
    protected void initView(View contentView) {
        layout = contentView;
        initViews();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onClickView(View view, int id) {
        switch (id) {
            default:
                break;
        }
    }

    @Override
    public void doAfterReConnectNewWork() {

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    private void initViews() {
        mList = SubjectTypeHelper.getFirstTypeList();
        listTitle = new ArrayList<>();
        listFragment = new ArrayList<>();
        mFragment = SelectTypeFragment.newInstance(mList,SelectTypeFragment.FIRST,SelectTypeFragment.TYPE_CHANNEL);
        listTitle.add("推荐");
        listTitle.add("关注");
        listTitle.add(getString(R.string.home_channel));
        listFragment.add(new HomeRecommendFragment());
        listFragment.add(new HomeAttentionFragment());
        listFragment.add(mFragment);

        mPager = (ViewPager) layout.findViewById(R.id.vp_fr_home_pager);
        ViewPagerAdapter mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), listFragment, listTitle);
        mPager.setAdapter(mPagerAdapter);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) layout
                .findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.tab_indicator,
                android.R.id.text1);
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(
                R.color.white));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mPager);
        slidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                if(arg0 == 2){

                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
}
