package com.binvshe.binvshe.binvsheui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binvshe.binvshe.R;

import org.srr.dev.adapter.RecyclerViewDataAdapter;
import org.srr.dev.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoi.
 * E-mail：KyluZoi@gmail.com
 * 2016/8/3
 */
public class SelectGoodsActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.rl_back_title)
    RelativeLayout mRlBackTitle;
    @Bind(R.id.rc_date)
    RecyclerView mRcDate;
    @Bind(R.id.rc_session)
    RecyclerView mRcSession;
    @Bind(R.id.rc_price)
    RecyclerView mRcPrice;
    @Bind(R.id.tvNext)
    TextView mTvNext;

    private ArrayList<Bean> mDatas=new ArrayList<>();
    private SelectGoodsAdapter mGoodsAdapter=new SelectGoodsAdapter();

    public static void start(Context context) {
        Intent starter = new Intent(context, SelectGoodsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initGetIntent() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_select_goods;
    }

    @Override
    public void initView() {
        initRec(mRcDate,mGoodsAdapter);
        initRec(mRcSession,mGoodsAdapter);
        mRcPrice.setLayoutManager(new LinearLayoutManager(this));
        mRcPrice.setAdapter(mGoodsAdapter);
        ButterKnife.bind(this);
        mGoodsAdapter.setData(mDatas);
    }

    private void initRec(RecyclerView recyclerview,SelectGoodsAdapter adapter) {
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void initData() {
        Bean bean=new Bean("10月1日%s\\n星期六%s",false);
        mDatas.add(bean);
        mDatas.add(bean);
        mDatas.add(bean);
        mDatas.add(bean);
        mDatas.add(bean);
        mDatas.add(bean);
        mDatas.add(bean);
        mDatas.add(bean);
        mDatas.add(bean);
        mGoodsAdapter.notifyDataSetChanged();

    }

    @Override
    public void refreshData() {

    }



    @OnClick({R.id.btn_title_back, R.id.tvNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_title_back:
                this.finish();
                break;
            case R.id.tvNext:
                IndentSureActivity.start(this);
                break;
        }
    }


    protected class SelectGoodsAdapter extends RecyclerViewDataAdapter<Bean, SelectGoodsAdapter.VH> {


        @Override
        public void onBindHolder(VH viewHolder, int i, Bean bean) {
            viewHolder.mTvContent.setText(bean.getText());
        }

        @Override
        public VH getViewHolder(View view) {
            return new VH(view);
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.item_select_goods;
        }

        public class VH extends RecyclerView.ViewHolder {
            @Bind(R.id.tv_content)
            TextView mTvContent;
            public VH(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }

    private class Bean {

        public Bean(String text,boolean isSelect)
        {
            this.text=text;
            this.isselect=isSelect;
        }

        private String text;
        private boolean isselect;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isselect() {
            return isselect;
        }

        public void setIsselect(boolean isselect) {
            this.isselect = isselect;
        }
    }
}
