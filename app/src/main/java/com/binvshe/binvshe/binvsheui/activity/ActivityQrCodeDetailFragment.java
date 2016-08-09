package com.binvshe.binvshe.binvsheui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.entity.ActivityList.SubOrdersEntity;
import com.binvshe.binvshe.http.model.GetOrderMsgModel;
import com.binvshe.binvshe.http.model.IViewModelInterface;
import com.binvshe.binvshe.http.response.CreateOrederResponse;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.LinearListFragment;
import org.srr.dev.util.UIL;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/9
 */
public class ActivityQrCodeDetailFragment extends LinearListFragment implements IViewModelInterface {
    private static final String KEY_ORDER_ID = "ORDER_ID";

    GetOrderMsgModel mGetOrderMsgModel;
    QrCodeAdapter mAdapter = new QrCodeAdapter();
    ArrayList<SubOrdersEntity> mdata = new ArrayList<>();

    String mOrderID = 0 + "";
    String mName;

    public static ActivityQrCodeDetailFragment newInstance(String orderID) {
        Bundle args = new Bundle();
        args.putString(KEY_ORDER_ID, orderID);
        ActivityQrCodeDetailFragment fragment = new ActivityQrCodeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {
        return mAdapter;
    }

    @Override
    public void onPullRefresh() {
        mGetOrderMsgModel.start(mOrderID);
    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            mOrderID = getArguments().getString(KEY_ORDER_ID);
        }
        initModel();
        mAdapter.setData(mdata);
        mGetOrderMsgModel.start(mOrderID);
    }

    private void initModel() {
        mGetOrderMsgModel = new GetOrderMsgModel();
        mGetOrderMsgModel.setViewModelInterface(this);
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
        if (tag == mGetOrderMsgModel.getTag()) {
            CreateOrederResponse response = (CreateOrederResponse) result;
            mName = response.getData().getName();
            mdata.clear();
            mdata.addAll(response.getData().getSubOrders());
            mAdapter.notifyDataSetChanged();
        }
        dismissSwiRefreshLayout();
    }

    @Override
    public void onFailLoad(int tag, int code, String codeMsg) {

    }

    @Override
    public void onExceptionLoad(int tag, Exception exception) {

    }

    public class QrCodeAdapter extends RecyclerViewDataAdapter<SubOrdersEntity, QrCodeAdapter.VHolder> {


        @Override
        public void onBindHolder(VHolder viewHolder, int i, SubOrdersEntity subOrdersEntity) {
            viewHolder.mTvActivityName.setText(mName);
            String imagePath="http://47.90.47.195/binvsheApp/app/orders/qrcode/"+subOrdersEntity.getId()+".png";
            UIL.load(viewHolder.mIvQrCode,imagePath);
        }

        @Override
        public VHolder getViewHolder(View view) {
            return new VHolder(view);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_code;
        }

        class VHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.iv_qr_code)
            ImageView mIvQrCode;
            @Bind(R.id.tv_activity_name)
            TextView mTvActivityName;
            public VHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
