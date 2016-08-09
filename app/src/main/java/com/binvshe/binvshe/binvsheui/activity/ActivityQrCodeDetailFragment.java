package com.binvshe.binvshe.binvsheui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.binvshe.binvshe.entity.ActivityList.SubOrdersEntity;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.LinearListFragment;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/9
 */
public class ActivityQrCodeDetailFragment extends LinearListFragment {
    private static final String KEY_ORDER_ID = "ORDER_ID";

    String mOrderID = 0 + "";

    public static ActivityQrCodeDetailFragment newInstance(String orderID) {
        Bundle args = new Bundle();
        args.putString(KEY_ORDER_ID, orderID);
        ActivityQrCodeDetailFragment fragment = new ActivityQrCodeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {
        return null;
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            mOrderID=getArguments().getString(KEY_ORDER_ID);
        }

    }

    public class QrCodeAdapter extends RecyclerViewDataAdapter<SubOrdersEntity, QrCodeAdapter.VHolder> {
        @Override
        public void onBindHolder(VHolder viewHolder, int i, SubOrdersEntity subOrdersEntity) {

        }

        @Override
        public VHolder getViewHolder(View view) {
            return null;
        }

        @Override
        public int getLayoutId(int viewType) {
            return 0;
        }

        class VHolder extends RecyclerView.ViewHolder {
            public VHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
