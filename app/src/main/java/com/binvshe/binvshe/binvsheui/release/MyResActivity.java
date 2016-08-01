package com.binvshe.binvshe.binvsheui.release;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binvshe.binvshe.R;
import com.binvshe.binvshe.binvsheui.dialog.ReleaseDialog;

import org.srr.dev.adapter.RecyclerViewAdapter;
import org.srr.dev.base.LinearListAcitivity;

public class MyResActivity extends LinearListAcitivity {
    public static final String TYPE = "com.binvshe.binvshe.release.type";

    private ResAdapter resAdapter;
    private String mType;

    @Override
    public RecyclerView.Adapter<RecyclerView.ViewHolder> setAdapter() {
        resAdapter = new ResAdapter();
        resAdapter.setOnItemClickLitener(new RecyclerViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View views, int position) {

            }

            @Override
            public boolean onItemLongClick(View views, int position) {
                return false;
            }
        });
        setSrlayoutEnable(false);
        return resAdapter;
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    protected void initGetIntent() {
        mType = getIntent().getStringExtra(TYPE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void refreshData() {

    }


    private class ResAdapter extends RecyclerViewAdapter {

        protected class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public void onBindHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder) viewHolder;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    @Override
    public View initHeader() {
        View v = getLayoutInflater().inflate(R.layout.ac_myres_create, null);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyResActivity.this, CreateResActivity.class);
                intent.putExtra(TYPE, mType);
                intent.putExtra(ReleaseDialog.UP_TYPE, getIntent().getStringExtra(ReleaseDialog.UP_TYPE));
                startActivity(intent);
            }
        });
        return v;
    }
}
