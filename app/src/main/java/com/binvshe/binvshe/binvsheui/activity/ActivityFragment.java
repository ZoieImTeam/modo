package com.binvshe.binvshe.binvsheui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.adapter.activityadapter.ActivityAdapter;
import com.binvshe.binvshe.adapter.myselfadapter.ChenRecyclerBaseAdapter.OnRecyclerItemClickListener;
import com.binvshe.binvshe.binvsheui.NaviActivity;
import com.binvshe.binvshe.constants.HttpConstanst;
import com.binvshe.binvshe.entity.ActivityList.ActivityData;
import com.binvshe.binvshe.entity.ActivityList.GetActivityList;
import com.binvshe.binvshe.http.model.GetActivityListModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetActivityListResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment implements IViewModelInterface {

    private List<String> listBanner = new ArrayList<>();
    private List<ActivityData> listActivity = new ArrayList<>();
    private int pageNo = 1;
    private int cur;
    private boolean isRefresh;
    private boolean isTouch;

    private NaviActivity mActivity;
    private View parent;
    private ViewPager pager;
    private ActivityAdapter activityAdapter;
    private GetActivityListModel getActivityListModel;
    private SwipeRefreshLayout layout_refresh;

    public ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parent = inflater.inflate(R.layout.fragment_activity, container, false);
        mActivity = (NaviActivity) getActivity();
        initView();
        initModel();
        return parent;
    }

    private void initModel() {
        getActivityListModel = new GetActivityListModel();
        getActivityListModel.setViewModelInterface(this);
        getActivityListModel.start(pageNo + "");
    }

    private void initView() {
        listBanner.add(HttpConstanst.BASE_URL + "/datas/files/binvsheApp/20160220/1455898437698.jpg");
//        listBanner.add(HttpConstanst.BASE_URL + "");
//        listBanner.add(HttpConstanst.BASE_URL + "");
        // 初始化
        initRefres();
        initRecyclerView();
    }

    private void initRefres() {
        layout_refresh = (SwipeRefreshLayout) parent.findViewById(R.id.activity_refresh);
        layout_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                getActivityListModel.start("1");
            }
        });
    }

    private View initHead() {
        cur = listBanner.size() * 20000;
        View head = mActivity.getLayoutInflater().inflate(R.layout.activity_head, null);
        pager = (ViewPager) head.findViewById(R.id.activity_head_page);
        HeadPagerAdapter headPagerAdapter = new HeadPagerAdapter(getChildFragmentManager());
        pager.setAdapter(headPagerAdapter);
        pager.setCurrentItem(cur);
        pager.addOnPageChangeListener(new HeadPageChangeListener());
//        pager.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (isTouch) {
//
//                } else {
//                    cur++;
//                    pager.setCurrentItem(cur);
//                }
//                pager.postDelayed(this, 5000);
//            }
//        }, 5000);
        return head;
    }

    private void initRecyclerView() {
//        View head = initHead();

        RecyclerView recycler = (RecyclerView) parent.findViewById(R.id.activity_recyclerview);
        recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        activityAdapter = new ActivityAdapter(listActivity, mActivity);
//        activityAdapter.addHeadView(head);
        activityAdapter.setOnRecyclerItemClickListener(new MyRecyclerItemClickListener());
        recycler.setAdapter(activityAdapter);
    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void onPreLoad(int tag) {

    }

    @Override
    public void onSuccessLoad(int tag, Object result) {
        if (isRefresh) {
            isRefresh = false;
            layout_refresh.setRefreshing(false);
            listActivity.clear();
            pageNo = 1;
        }
        pageNo++;
        GetActivityListResponse response = (GetActivityListResponse) result;
        GetActivityList data = response.getData();
        listActivity.addAll(data.getDatas());
        activityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        layout_refresh.setRefreshing(false);
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        layout_refresh.setRefreshing(false);
    }

    private class MyRecyclerItemClickListener implements OnRecyclerItemClickListener {
        @Override
        public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
            Intent intent = new Intent(mActivity, DetailActivityActivity.class);
            intent.putExtra(DetailActivityActivity.ACTIVITYID, listActivity.get(position).getId());
            startActivity(intent);
        }
    }

    private class HeadPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                // 滑动时候
                case ViewPager.SCROLL_STATE_DRAGGING:
                    isTouch = true;
                    break;
                // 没动
                case ViewPager.SCROLL_STATE_IDLE:
                    isTouch = false;
                    break;
                // 滑动完毕
                case ViewPager.SCROLL_STATE_SETTLING:
                    isTouch = false;
                    break;
            }
        }
    }

    private class HeadPagerAdapter extends FragmentPagerAdapter {

        public HeadPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ActiviyHeadFragment(listBanner.get(position % listBanner.size()));
        }

        @Override
        public int getCount() {
            return cur * 2;
        }
    }


}
