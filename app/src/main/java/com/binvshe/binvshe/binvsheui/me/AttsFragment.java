package com.binvshe.binvshe.binvsheui.me;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.adapter.meadapter.AttsAdapter;
import com.binvshe.binvshe.adapter.myselfadapter.ChenRecyclerBaseAdapter.OnRecyclerItemClickListener;
import com.binvshe.binvshe.binvsheui.utils.SpUtils;
import com.binvshe.binvshe.constants.Constants;
import com.binvshe.binvshe.entity.fans.FanData;
import com.binvshe.binvshe.entity.fans.GetFans;
import com.binvshe.binvshe.http.model.GetFansModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.GetFansResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttsFragment extends Fragment implements IViewModelInterface {

    private AttsActivity mActivity;
    private boolean isAtts;
    private boolean isNext;
    private boolean isGetMore;
    private int pageNo = 1;
    private int type;

    private List<FanData> mList = new ArrayList<>();
    private Map<Integer, Boolean> hmAtts = new HashMap<>();
    private AttsAdapter attsAdapter;
    private GetFansModel getFansModel;
    private String userID;

    public AttsFragment(boolean isAtts) {
        this.isAtts = isAtts;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = (AttsActivity) getActivity();
        View parent = inflater.inflate(R.layout.fragment_atts, container, false);
        initView(parent);
        initData();
        return parent;
    }

    private void initData() {
        userID = SpUtils.getUserID();
        if (isAtts) {
            type = Constants.TYPE.ATTS;
        } else {
            type = Constants.TYPE.FANS;
        }
        getFansModel = new GetFansModel();
        getFansModel.setViewModelInterface(this);
        getFansModel.start(userID, type, pageNo);
    }

    private void initView(View parent) {
        RecyclerView recycler = (RecyclerView) parent.findViewById(R.id.atts_recyclerview);
        recycler.setLayoutManager(new LinearLayoutManager(mActivity));
        attsAdapter = new AttsAdapter(mList, hmAtts, isAtts);
        recycler.setAdapter(attsAdapter);
        recycler.addOnScrollListener(new MyScrollListener());
        attsAdapter.setOnRecyclerItemClickListener(new MyRecyclerItemClickListener());
    }

    private class MyRecyclerItemClickListener implements OnRecyclerItemClickListener {

        @Override
        public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
            // TODO: 2016/1/25 单击事件
            Toast.makeText(mActivity, "" + position, Toast.LENGTH_SHORT).show();
        }
    }

    private class MyScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (!isNext) {
                return;
            }
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
            int totalItemCount = manager.getItemCount();
            if (lastVisibleItem == totalItemCount - 1) {
                if (isGetMore) {
                    return;
                }
                // 加载更多
                getMoreData();
            }
        }
    }

    private void getMoreData() {
        isGetMore = true;
        getFansModel.start(userID, type, pageNo);
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
        GetFansResponse response = (GetFansResponse) result;
        GetFans data = response.getData();
        mList.addAll(data.getDatas());
        attsAdapter.notifyDataSetChanged();
        isNext = data.getNext();
        pageNo = data.getCurPage();
        pageNo++;
        isGetMore = false;
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {
        Toast.makeText(mActivity, codeMsg, Toast.LENGTH_SHORT).show();
        isGetMore = false;
    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {
        Toast.makeText(mActivity, getString(R.string.exception), Toast.LENGTH_SHORT).show();
        isGetMore = false;
    }
}
