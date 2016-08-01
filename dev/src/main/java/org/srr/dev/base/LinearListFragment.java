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
public abstract class LinearListFragment extends BaseFragment {


    private XRecyclerView mList;
    private SwipeRefreshLayout srl;

    public LinearListFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fr_linear_list;
    }

    @Override
    protected void initView(View contentView) {
        srl = findView(R.id.srl_fr_linear_list);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onPullRefresh();
            }
        });
        mList = (XRecyclerView) contentView.findViewById(R.id.rv_fr_linear_list);
        mList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                onLoadingMore();
            }
        });
        View header = initHeader();
        View footer = initFooter();
        if (header != null) {
            mList.addHeaderView(header);
        }
        if (footer != null) {
            mList.addFootView(footer);
        }
        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setAdapter(setAdapter());
    }

    /**
     * 设置LayoutManger
     * @param layout
     * @return
     */
    public void setLayoutManager(RecyclerView.LayoutManager layout){
        mList.setLayoutManager(layout);
    }

    public XRecyclerView getmList() {
        return mList;
    }


    @Override
    public void doAfterReConnectNewWork() {

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

    public void onLoadingMore() {
    }

    public View initHeader() {
        return null;
    }

    public View initFooter() {
        return null;
    }

}
