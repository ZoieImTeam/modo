package org.srr.dev.base;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.srr.dev.R;

import org.srr.dev.util.UIL;
import org.srr.dev.view.xrecyclerview.XRecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class LinearListAcitivity extends BaseActivity   {


    private XRecyclerView mList;
    private SwipeRefreshLayout srl;

    public LinearListAcitivity() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fr_linear_list;
    }

    @Override
    public void initView() {
        srl = findView(R.id.srl_fr_linear_list);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onPullRefresh();
            }
        });
        mList = (XRecyclerView)findViewById(R.id.rv_fr_linear_list);
        View header = initHeader();
        View footer = initFooter();
        if (header != null) {
            mList.addHeaderView(header);
        }
        if (footer != null) {
            mList.addFootView(footer);
        }
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setAdapter(setAdapter());
        mList.addOnScrollListener(UIL.getImageLoaderPauseScrollListener());
    }

    public RecyclerView getmList() {
        return mList;
    }

    public void setSrlayoutEnable(boolean enabled) {
        srl.setEnabled(enabled);
    }

    public void showSwiRefreshLayout() {
        if (srl != null) {
            srl.setRefreshing(true);
        }
    }

    public void dismissSwiRefreshLayout() {
        if (srl != null) {
            srl.setRefreshing(false);
        }
    }

    public abstract RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter();

    public abstract void onPullRefresh();

    public View initHeader() {
        return null;
    }

    public View initFooter() {
        return null;
    }
}
